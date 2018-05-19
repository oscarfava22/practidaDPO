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
 *
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
     *
     * @param mainView
     * @param platosManager
     * @param reservasManager
     * @param pedidosManager
     * @param pedidosListListener
     * @param gestionCartaViewListener
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
     *
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
     *
     * @param entryDedicatedServer
     */
    public void removeDedicatedServer(ReservaDedicatedServer entryDedicatedServer) {
        reservaDedicatedServers.remove(entryDedicatedServer);
    }

    /**
     *
     * @return
     */
    public int getDedicatedServersCount() {
        return reservaDedicatedServers.size();
    }

    /**
     *
     */
    public void sendBroadcast() {
        for(ReservaDedicatedServer rds : reservaDedicatedServers) {
            rds.updateMessageToClient();
        }
    }

    /**
     *
     * @param id
     * @param name
     */
    public void sendServirPlatoToClient(long id, String name) {
        for(ReservaDedicatedServer rds : reservaDedicatedServers) {
            if (rds.getClientName().equals(name)) {
                rds.sendServirPlato(id);
            }
        }
    }

    /**
     *
     */
    public void updatePedidosView() {
        mainView.getGestionPedidosView().getPedidosView().initView(pedidosManager.getPedidos());
        mainView.getGestionPedidosView().getPedidosView().registerControllers(pedidosListListener);
    }

    /**
     *
     */
    public void updatePlatosView() {
        mainView.getGestionCartaView().getPlatosView().initPlatosView(platosManager.getPlatos());
        mainView.getGestionCartaView().getPlatosView().registerControllers(gestionCartaViewListener);
        mainView.getGestionCartaView().getPlatosOptionsView().resetTextFields();
    }
}
