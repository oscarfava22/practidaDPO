package servidor.network;

import servidor.model.PlatosManager;
import servidor.model.ReservasManager;
import servidor.view.MainView;

public class MainServer {

    private MainView mainView;
    private PlatosManager platosManager;
    private ReservasManager reservasManager;

    private EntryServer entryServer;
    private ReservaServer reservaServer;

    public MainServer(MainView mainView, PlatosManager platosManager, ReservasManager reservasManager) {

        this.mainView = mainView;
        this.platosManager = platosManager;
        this.reservasManager = reservasManager;

        entryServer = new EntryServer(this, mainView, platosManager, reservasManager);
        reservaServer = new ReservaServer(this, mainView, platosManager, reservasManager);
    }

    public void initServers() {
        entryServer.start();
        reservaServer.start();
    }

    public void sendEntryServerBroadcast() {
        entryServer.sendBroadcast();
    }

    public void sendResrvaServerBroadcast() {
        entryServer.sendBroadcast();
    }

    public void sendGlobalBroadcast() {
        sendEntryServerBroadcast();
        sendResrvaServerBroadcast();
    }
}
