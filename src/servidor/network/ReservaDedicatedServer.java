package servidor.network;

import Model.Plat;
import servidor.model.*;
import servidor.view.MainView;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import java.util.LinkedList;

/**
 * Servidor dedicado para cada Reserva Client que se conecte, permite gestionar de forma independiente cada cliente,
 * ofreciendo una comunicacion en paralelo con todos los clientes.
 */
public class ReservaDedicatedServer extends Thread {

    private ReservaServer reservaServer;
    private Socket reservaClientSocket;
    private MainView mainView;
    private PlatosManager platosManager;
    private ReservasManager reservasManager;
    private PedidosManager pedidosManager;
    private boolean isRunning;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private String name = "";
    private String password = "";

    /**
     * Constructor principal que prepara todas las variables que seran utilizadas por el servidor dedicado.
     * @param reservaServer Reserva Server para permitir que este servidor dedicado notifique al principal.
     * @param reservaClientSocket socket que ha abierto el "Reserva Server" para este servidor dedicado.
     * @param mainView vista principal del programa.
     * @param platosManager gestor de platos (Carta).
     * @param reservasManager gestor de reservas.
     * @param pedidosManager gestor de pedidos.
     */
    public ReservaDedicatedServer(ReservaServer reservaServer, Socket reservaClientSocket, MainView mainView,
                                  PlatosManager platosManager, ReservasManager reservasManager,
                                  PedidosManager pedidosManager) {

        this.reservaServer = reservaServer;
        this.reservaClientSocket = reservaClientSocket;
        this.mainView = mainView;
        this.platosManager = platosManager;
        this.reservasManager = reservasManager;
        this.pedidosManager = pedidosManager;
        isRunning = false;
    }

    /**
     * Metodo que inicializa el servidor dedicado, que se mantendra en ejecuccion constantemente mientras exista la
     * conexion con el cliente Reserva.
     * Este servidor dedicado esta en constante modo de lectura esperando a recibir un tipo de mensaje determinado
     * que puede ser de distintas clases, de manera que para cada tipo de mensaje recibido se realiza una determinada
     * acción.
     * Si se recibe un objeto de la clase (String), este puede ser de 2 tipos; "PAGAR" significando que el usuario
     * desea pagar su pedido y finalizar la conexion, en cualquier otro caso el "String" recibido será una solicitud de
     * inicio de sesion por parte del cliente, de modo que se procedera a buscar si esa persona tiene una reserva
     * realizada para poder autorizarle el acceso al sistema.
     * Si el objeto recibido no es un "String" entonces será una lista de platos procedentes del cliente, que
     * representaran el pedido realizado, y el servidor realizara una busqueda de la reserva y actualizara su estado a
     * "2" significando que se ha recibido el pedido del cliente y se añadiran los platos enviados a la lista de
     * platos pendientes dedicatada para cada reserva.
     */
    @Override
    public void run() {

        try {
            ois = new ObjectInputStream(reservaClientSocket.getInputStream());
            oos = new ObjectOutputStream(reservaClientSocket.getOutputStream());

            isRunning = true;
            Reserva reserva = new Reserva();

            while (isRunning) {

                Object object = ois.readObject();

                if (object.getClass().equals(String.class)) {

                    String string = (String) object;

                    switch(string) {

                        case "PAGAR":
                            //Se actualiza el estado de la reserva marcandola con un 3 como finalizada
                            reservasManager.updateReservaState(reserva, 3);
                            //Se actualiza la vista de los pedidos
                            reservaServer.updatePedidosView();
                            //Se reinician los campos para identificar al siguiente usuario que se conecte
                            name = "";
                            password = "";
                            break;

                        default:
                            //El formato de string recibido es (user%%pass)
                            String[] s = string.split("%%");

                            if ((reserva = reservasManager.searchReserva(s[0], s[1])) != null) {
                                //Si el usuario tiene una reserva se asigna su nombre y contraseña
                                name = s[0];
                                password = s[1];
                                //Se añade un pedido nuevo en el gestor de pedidos preparado para recibir platos
                                pedidosManager.addPedido(new Pedido(reserva));
                                //Se envia un mensaje de confirmacion y posteriormente la lista de platos disponibles
                                oos.writeObject("OK");
                                updateMessageToClient();

                            } else {
                                oos.writeObject("KO");
                            }

                            break;
                    }

                } else {
                    //En espera de recibir los platos que vaya solicitando el cliente
                    ArrayList<Plat> plats = (ArrayList<Plat>) object;
                    LinkedList<Plato> platos = new LinkedList<>();

                    //Conversion de los datos enviados por el cliente al modelo interno utilizado por el servidor
                    for (Plat plat : plats) {
                        Plato plato = new Plato(plat.getId(), String.valueOf(plat.getTipus()), plat.getNom(),
                                                plat.getPrice(), plat.getUnitatsSeleccionades());
                        platos.add(plato);
                        //Se actualizan las unidades de los platos de la carta restando los pedidos por el cliente
                        if (platosManager.getPlato(plato.getId()).getUnits() - plato.getId() < 0) {
                             platosManager.getPlato(plato.getId()).setUnits(0);
                        } else {
                            platosManager.getPlato(plato.getId()).updateUnits(-plato.getUnits());
                        }
                    }

                    //Se aprovecha el metodo del Reserva Server para actualizar la vista de los platos de la carta
                    reservaServer.updatePlatosView();
                    //Se realiza un Broadcast a todos los clientes para que actualizen su carta de platos
                    reservaServer.sendBroadcast();
                    //Se actualiza el estado de la reserva a 2 significando que el usuario ha realizado un pedido
                    reservasManager.updateReservaState(reserva, 2);

                    pedidosManager.getPedidoByReservaName(name).addPlatosPendientes(platos);
                    reservaServer.updatePedidosView();
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            reservaServer.removeDedicatedServer(this);
            mainView.setConnectedDevices(reservaServer.getDedicatedServersCount());
        }
        finally {
            try {
                ois.close();
            } catch (IOException e) {}
            try {
                oos.close();
            } catch (IOException e) {}
            try {
                reservaClientSocket.close();
            } catch (IOException e) {}
        }
    }

    /**
     * Metodo para enviar una actualizacion de los platos disponibles en el sistema al cliente.
     */
    public void updateMessageToClient() {
        try {
            oos.reset();
            oos.writeObject(platosManager.getAvailablePlatos());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permite servir un plato al cliente enviandole el identificador del plato en cuestion.
     * @param id del plato a servir
     */
    public void sendServirPlato(long id) {

        Long ID = id;
        try {
            oos.writeObject(ID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permite obtener el nombre de la persona que se ha conectado a traves del cliente Reserva.
     * @return el nombre de la persona que se ha conectado.
     */
    public String getClientName() {
        return name;
    }

}
