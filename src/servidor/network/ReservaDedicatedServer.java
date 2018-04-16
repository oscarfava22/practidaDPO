package servidor.network;

import servidor.model.PlatosManager;

import java.io.*;
import java.net.Socket;

public class ReservaDedicatedServer extends Thread {

    private ReservaServer reservaServer;
    private Socket reservaClientSocket;
    private PlatosManager platosManager;
    private boolean isRunning;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private DataInputStream dis;
    private DataOutputStream dos;

    public ReservaDedicatedServer(ReservaServer reservaServer,Socket reservaClientSocket, PlatosManager platosManager) {
        this.reservaServer = reservaServer;
        this.reservaClientSocket = reservaClientSocket;
        this.platosManager = platosManager;
        isRunning = false;
    }

    @Override
    public void run() {

        try {
            ois = new ObjectInputStream(reservaClientSocket.getInputStream());
            oos = new ObjectOutputStream(reservaClientSocket.getOutputStream());
            dis = new DataInputStream(reservaClientSocket.getInputStream());
            dos = new DataOutputStream(reservaClientSocket.getOutputStream());

            isRunning = true;

            while (isRunning) {

                //TODO Reserva Comm Protocol
            }


        } catch (IOException  e) {
            //e.printStackTrace();
            System.out.println("A Reserva Client Disconnected from Server");
            reservaServer.removeDedicatedServer(this);
        }
        finally {
            try {
                ois.close();
            } catch (IOException e) {}
            try {
                oos.close();
            } catch (IOException e) {}
            try {
                dis.close();
            } catch (IOException e) {}
            try {
                dos.close();
            } catch (IOException e) {}
            try {
                reservaClientSocket.close();
            } catch (IOException e) {}
        }
    }

    public void updateMessageToClient() {

        /*try {

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


}
