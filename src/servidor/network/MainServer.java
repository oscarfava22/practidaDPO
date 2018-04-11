package servidor.network;

import servidor.model.PlatosManager;
import servidor.view.MainView;

public class MainServer {

    private MainView mainView;
    private PlatosManager platosManager;
    private EntryServer entryServer;
    private ReservaServer reservaServer;

    public MainServer(MainView mainView, PlatosManager platosManager) {

        this.mainView = mainView;
        this.platosManager = platosManager;

        entryServer = new EntryServer(this, mainView, platosManager);
        reservaServer = new ReservaServer(this, mainView, platosManager);
    }


    public void initServers() {
        entryServer.start();
        reservaServer.start();
    }

}
