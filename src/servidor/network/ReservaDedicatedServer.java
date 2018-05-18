package servidor.network;

import Model.Plat;
import servidor.model.*;
import servidor.view.MainView;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import java.util.LinkedList;

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

    public ReservaDedicatedServer(ReservaServer reservaServer, Socket reservaClientSocket, MainView mainView,
                                  PlatosManager platosManager, ReservasManager reservasManager, PedidosManager pedidosManager) {
        this.reservaServer = reservaServer;
        this.reservaClientSocket = reservaClientSocket;
        this.mainView = mainView;
        this.platosManager = platosManager;
        this.reservasManager = reservasManager;
        this.pedidosManager = pedidosManager;
        isRunning = false;
    }

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
                            Pedido tmp = pedidosManager.getPedidoByReservaName(name);
                            reservasManager.updateReservaState(reserva, 3);
                            reservaServer.updatePedidosView();
                            name = "";
                            password = "";
                            break;

                        default:

                            String[] s = string.split("%%");
                            System.out.println(string);

                            if ((reserva = reservasManager.searchReserva(s[0], s[1])) != null) {
                                name = s[0];
                                password = s[1];
                                oos.writeObject("OK");
                                pedidosManager.addPedido(new Pedido(reserva));
                                updateMessageToClient();

                            } else {
                                oos.writeObject("KO");
                            }

                            break;
                    }

                } else {

                    ArrayList<Plat> plats = (ArrayList<Plat>) object;
                    LinkedList<Plato> platos = new LinkedList<>();

                    for (Plat plat : plats) {
                        Plato plato = new Plato(plat.getId(), String.valueOf(plat.getTipus()), plat.getNom(), plat.getPrice(), plat.getUnitatsSeleccionades());
                        platos.add(plato);
                        platosManager.getPlato(plato.getId()).updateUnits(-plato.getUnits());
                    }

                    reservaServer.updatePlatosView();
                    reservaServer.sendBroadcast();

                    reservasManager.updateReservaState(reserva, 2);

                    pedidosManager.getPedidoByReservaName(name).addPlatosPendientes(platos);
                    reservaServer.updatePedidosView();
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
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


    public void updateMessageToClient() {
        try {
            oos.reset();
            oos.writeObject(platosManager.getAvailablePlatos());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendServirPlato(long id) {

        Long ID = id;
        try {
            oos.writeObject(ID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getClientName() {
        return name;
    }

    public String getClientPassword() {
        return password;
    }

}
