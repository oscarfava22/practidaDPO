package servidor.network;

import servidor.model.PlatosManager;

import java.net.Socket;

public class ReservaDedicatedServer extends Thread {

    private ReservaServer reservaServer;
    private Socket reservaClientSocket;
    private PlatosManager platosManager;
    private boolean isRunning;

    public ReservaDedicatedServer(ReservaServer reservaServer,Socket reservaClientSocket, PlatosManager platosManager) {
        this.reservaServer = reservaServer;
        this.reservaClientSocket = reservaClientSocket;
        this.platosManager = platosManager;
        isRunning = false;
    }

    @Override
    public void run() {

    }


}
