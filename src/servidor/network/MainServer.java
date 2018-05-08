package servidor.network;

import servidor.model.PedidosManager;
import servidor.model.Plato;
import servidor.model.PlatosManager;
import servidor.model.ReservasManager;
import servidor.view.MainView;

import java.util.LinkedList;

public class MainServer {

    private MainView mainView;
    private PlatosManager platosManager;
    private ReservasManager reservasManager;
    private PedidosManager pedidosManager;

    private EntryServer entryServer;
    private ReservaServer reservaServer;

    public MainServer(MainView mainView, PlatosManager platosManager, ReservasManager reservasManager, PedidosManager pedidosManager) {

        this.mainView = mainView;
        this.platosManager = platosManager;
        this.reservasManager = reservasManager;
        this.pedidosManager = pedidosManager;

        entryServer = new EntryServer(this, mainView, platosManager, reservasManager);
        reservaServer = new ReservaServer(this, mainView, platosManager, reservasManager, pedidosManager);
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
