package servidor.network;

import servidor.model.PlatosManager;
import servidor.model.Reserva;
import servidor.view.MainView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ReservaServer extends Thread {

    private MainServer mainServer;
    private ServerSocket reservaServerSocket;
    private boolean isRunning;

    private MainView mainView;
    private PlatosManager platosManager;

    public ReservaServer(MainServer mainServer, MainView mainView, PlatosManager platosManager) {

        this.mainServer = mainServer;
        this.mainView = mainView;
        this.platosManager = platosManager;

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
                Socket reservaSocket = reservaServerSocket.accept();
                System.out.println("Reserva Client connected");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
