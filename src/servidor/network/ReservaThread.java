package servidor.network;

import servidor.model.Reserva;

import java.io.IOException;
import java.net.ServerSocket;

public class ReservaThread extends Thread {

    private ServerSocket reservaSocket;

    public ReservaThread() {

        try {
            reservaSocket = new ServerSocket(Network.RESERVA_SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
