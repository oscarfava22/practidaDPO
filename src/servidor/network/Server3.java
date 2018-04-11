package servidor.network;

import servidor.model.PlatosManager;
import servidor.view.MainView;

import java.io.IOException;
import java.net.ServerSocket;

public class Server3 {

    private MainView mainView;
    private PlatosManager platosManager;

    public Server3(MainView mainView, PlatosManager platosManager) {
        this.mainView = mainView;
        this.platosManager = platosManager;
    }


    public void initSockets() {

        EntryThread entrySocket = new EntryThread();
        ReservaThread reservaSocket = new ReservaThread();

        entrySocket.start();
        reservaSocket.start();
    }

}
