package servidor.network;

import servidor.controller.GestionCartaViewListener;
import servidor.controller.PedidosListListener;
import servidor.model.PedidosManager;
import servidor.model.Plato;
import servidor.model.PlatosManager;
import servidor.model.ReservasManager;
import servidor.view.GestionCartaView;
import servidor.view.MainView;

import java.util.LinkedList;

public class MainServer {

    private MainView mainView;
    private PlatosManager platosManager;
    private ReservasManager reservasManager;
    private PedidosManager pedidosManager;
    private PedidosListListener pedidosListListener;
    private GestionCartaViewListener gestionCartaViewListener;

    private EntryServer entryServer;
    private ReservaServer reservaServer;

    public MainServer(MainView mainView, PlatosManager platosManager, ReservasManager reservasManager,
                      PedidosManager pedidosManager, PedidosListListener pedidosListListener,
                      GestionCartaViewListener gestionCartaViewListener) {

        this.mainView = mainView;
        this.platosManager = platosManager;
        this.reservasManager = reservasManager;
        this.pedidosManager = pedidosManager;
        this.pedidosListListener = pedidosListListener;
        this.gestionCartaViewListener = gestionCartaViewListener;

        entryServer = new EntryServer(this, mainView, platosManager, reservasManager);
        reservaServer = new ReservaServer(this, mainView, platosManager, reservasManager, pedidosManager, this.pedidosListListener, this.gestionCartaViewListener);
    }

    public void initServers() {
        entryServer.start();
        reservaServer.start();
    }

    //TODO
    public void sendEntryServerBroadcast() {
        entryServer.sendBroadcast();
    }
    //TODO
    public void sendReservaServerBroadcast() {
        reservaServer.sendBroadcast();
    }

    public ReservaServer getReservaServer() {
        return reservaServer;
    }
    //TODO
    /*public void sendGlobalBroadcast() {
        sendEntryServerBroadcast();
        sendReservaServerBroadcast();
    }*/
}
