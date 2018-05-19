package servidor.network;

import servidor.controller.GestionCartaViewListener;
import servidor.controller.PedidosListListener;
import servidor.model.*;
import servidor.view.MainView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Servidor que gestiona la comunicacion con todos los "ReservaDedicatedServers"
 */
public class ReservaServer extends Thread {

    private ServerSocket reservaServerSocket;
    private boolean isRunning;
    private MainView mainView;
    private PlatosManager platosManager;
    private ReservasManager reservasManager;
    private PedidosManager pedidosManager;
    private PedidosListListener pedidosListListener;
    private GestionCartaViewListener gestionCartaViewListener;
    private LinkedList<ReservaDedicatedServer> reservaDedicatedServers;

    /**
     * Constructor que prepara el ServerSocket con el puerto establecido e inicializa las variables que se utilizaran
     * durante la ejecuccion del servidor.
     * @param mainView vista principal del programa
     * @param platosManager gestor de platos
     * @param reservasManager gestor de reservas
     * @param pedidosManager gestror de pedidos
     * @param pedidosListListener controlador de la lista de pedidos
     * @param gestionCartaViewListener controlador de la vista de la carta
     */
    public ReservaServer(MainView mainView, PlatosManager platosManager,
                         ReservasManager reservasManager, PedidosManager pedidosManager,
                         PedidosListListener pedidosListListener, GestionCartaViewListener gestionCartaViewListener) {

        try {
            reservaServerSocket = new ServerSocket(Network.RESERVA_SERVER_PORT);
            reservaDedicatedServers = new LinkedList<>();

            this.mainView = mainView;
            this.platosManager = platosManager;
            this.reservasManager = reservasManager;
            this.pedidosManager = pedidosManager;
            this.pedidosListListener = pedidosListListener;
            this.gestionCartaViewListener = gestionCartaViewListener;

            isRunning = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que inicializa el servidor Reserva, que estara continuamente esperando la conexion de nuevos clientes,
     * cuando detecte una conexion en el puerto establecido, procedera a ejecutar un nuevo Thread con un servidor
     * dedicado para gestionar independientemente cada conexion que se establezca, añadira el nuevo servidor dedicado
     * a una lista interna para poder gestionarlos todos.
     */
    @Override
    public void run() {

        isRunning = true;

        while(isRunning) {

            try {
                Socket reservaClientSocket = reservaServerSocket.accept();

                ReservaDedicatedServer rds = new ReservaDedicatedServer(this, reservaClientSocket, mainView,
                                                                        platosManager, reservasManager, pedidosManager);

                reservaDedicatedServers.add(rds);
                rds.start();

                mainView.setConnectedDevices(reservaDedicatedServers.size());

            } catch (IOException e) {
                e.printStackTrace();
                isRunning = false;
            }
        }
    }

    /**
     * Permite eliminar un servidor dedicado cuando se detecte que el cliente se ha desconectado.
     * @param entryDedicatedServer servidor dedicado que se quiere eliminar.
     */
    public void removeDedicatedServer(ReservaDedicatedServer entryDedicatedServer) {
        reservaDedicatedServers.remove(entryDedicatedServer);
    }

    /**
     * Permite conocer el numero actual de servidores dedicados en ejecuccion y por consiguiente el numero de clientes
     * conectados.
     * @return el numero de clientes "Reserva" conectados
     */
    public int getDedicatedServersCount() {
        return reservaDedicatedServers.size();
    }

    /**
     * Permite retransmitir un mensaje en "Broadcast" a todos los servidores dedicados en ejecuccion.
     */
    public void sendBroadcast() {
        for(ReservaDedicatedServer rds : reservaDedicatedServers) {
            rds.updateMessageToClient();
        }
    }

    /**
     * Permite servir un plato a un cliente conociendo solo el identificador del plato y el nombre del cliente,
     * el servidor buscara entre todos los servidores dedicados conectados el cliente al cual servir el plato.
     * @param id identificador del plato a servir.
     * @param name nombre del cliente al cual servir el plato.
     */
    public void sendServirPlatoToClient(long id, String name) {
        for(ReservaDedicatedServer rds : reservaDedicatedServers) {
            if (rds.getClientName().equals(name)) {
                rds.sendServirPlato(id);
            }
        }
    }

    /**
     * Permite actualizar la vista de los pedidos.
     */
    public void updatePedidosView() {
        mainView.getGestionPedidosView().getPedidosView().initView(pedidosManager.getPedidos());
        mainView.getGestionPedidosView().getPedidosView().registerControllers(pedidosListListener);
    }

    /**
     * Permite actualizar la vista de los platos (Carta).
     */
    public void updatePlatosView() {
        mainView.getGestionCartaView().getPlatosView().initPlatosView(platosManager.getPlatos());
        mainView.getGestionCartaView().getPlatosView().registerControllers(gestionCartaViewListener);
        mainView.getGestionCartaView().getPlatosOptionsView().resetTextFields();
    }
}
