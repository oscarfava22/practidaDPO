package servidor.network;

import servidor.controller.GestionCartaViewListener;
import servidor.controller.PedidosListListener;
import servidor.model.*;
import servidor.view.MainView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ReservaServer extends Thread {

    private MainServer mainServer;
    private ServerSocket reservaServerSocket;
    private boolean isRunning;

    private MainView mainView;
    private PlatosManager platosManager;
    private ReservasManager reservasManager;
    private PedidosManager pedidosManager;
    private PedidosListListener pedidosListListener;
    private GestionCartaViewListener gestionCartaViewListener;

    private LinkedList<ReservaDedicatedServer> reservaDedicatedServers;

    public ReservaServer(MainServer mainServer, MainView mainView, PlatosManager platosManager,
                         ReservasManager reservasManager, PedidosManager pedidosManager,
                         PedidosListListener pedidosListListener, GestionCartaViewListener gestionCartaViewListener) {

        this.mainServer = mainServer;
        this.mainView = mainView;
        this.platosManager = platosManager;
        this.reservasManager = reservasManager;
        this.pedidosManager = pedidosManager;
        this.pedidosListListener = pedidosListListener;
        this.gestionCartaViewListener = gestionCartaViewListener;

        reservaDedicatedServers = new LinkedList<>();

        try {
            reservaServerSocket = new ServerSocket(Network.RESERVA_SERVER_PORT);
            isRunning = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        isRunning = true;

        while(isRunning) {

            try {
                System.out.println("Waiting for a Reserva client...");
                Socket reservaClientSocket = reservaServerSocket.accept();
                System.out.println("Reserva Client connected");

                ReservaDedicatedServer reservaDedicatedServer = new ReservaDedicatedServer(this, reservaClientSocket, mainView, platosManager, reservasManager, pedidosManager);

                reservaDedicatedServers.add(reservaDedicatedServer);
                reservaDedicatedServer.start();

                mainView.setConnectedDevices(reservaDedicatedServers.size());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeDedicatedServer(ReservaDedicatedServer entryDedicatedServer) {
        reservaDedicatedServers.remove(entryDedicatedServer);
    }

    public int getDedicatedServersCount() {
        return reservaDedicatedServers.size();
    }

    public void sendBroadcast() {
        for(ReservaDedicatedServer rds : reservaDedicatedServers) {
            rds.updateMessageToClient();
        }
    }

    public void sendServirPlatoToClient(long id, String name) {
        for(ReservaDedicatedServer rds : reservaDedicatedServers) {
            if (rds.getClientName().equals(name)) {
                rds.sendServirPlato(id);
            }
        }
    }

    public LinkedList<ReservaDedicatedServer> getReservaDedicatedServers() {
        return reservaDedicatedServers;
    }

    public void updatePedidosView() {
        mainView.getGestionPedidosView().getPedidosView().initView(pedidosManager.getPedidos());
        mainView.getGestionPedidosView().getPedidosView().registerControllers(pedidosListListener);
    }

    public void updatePlatosView() {
        mainView.getGestionCartaView().getPlatosView().initPlatosView(platosManager.getPlatos());
        mainView.getGestionCartaView().getPlatosView().registerControllers(gestionCartaViewListener);
        mainView.getGestionCartaView().getPlatosOptionsView().resetTextFields();
    }
}
