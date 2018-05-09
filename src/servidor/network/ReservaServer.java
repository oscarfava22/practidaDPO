package servidor.network;

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

    private LinkedList<ReservaDedicatedServer> reservaDedicatedServers;

    public ReservaServer(MainServer mainServer, MainView mainView, PlatosManager platosManager, ReservasManager reservasManager, PedidosManager pedidosManager) {

        this.mainServer = mainServer;
        this.mainView = mainView;
        this.platosManager = platosManager;
        this.reservasManager = reservasManager;
        this.pedidosManager = pedidosManager;

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

    public void updatePedidosView() {
        //reservasManager.addReserva();
        mainView.getGestionPedidosView().getPedidosView().initView(reservasManager.getReservas());
        mainView.getGestionPedidosView().getPedidosView().registerControllers(new PedidosListListener(mainView, pedidosManager,platosManager));
    }
}
