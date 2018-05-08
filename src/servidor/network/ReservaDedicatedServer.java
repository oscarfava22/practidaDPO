package servidor.network;

import servidor.model.PlatosManager;
import servidor.view.MainView;

import java.io.*;
import java.net.Socket;

public class ReservaDedicatedServer extends Thread {

    private ReservaServer reservaServer;
    private Socket reservaClientSocket;
    private MainView mainView;
    private PlatosManager platosManager;
    private boolean isRunning;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private DataInputStream dis;
    private DataOutputStream dos;

    public ReservaDedicatedServer(ReservaServer reservaServer, Socket reservaClientSocket, MainView mainView, PlatosManager platosManager) {
        this.reservaServer = reservaServer;
        this.reservaClientSocket = reservaClientSocket;
        this.mainView = mainView;
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

                String s = (String) ois.readObject();
                System.out.println(s);
                if (s.equals("Alex%%1234")) {
                    System.out.println("OK");
                    oos.writeObject("OK");

                    reservaServer.updatePedidosView();
                    updateMessageToClient();
                } else {
                    oos.writeObject("KO");
                }
            }


        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
            reservaServer.removeDedicatedServer(this);
            mainView.setConnectedDevices(reservaServer.getDedicatedServersCount());
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
        try {
            oos = new ObjectOutputStream(reservaClientSocket.getOutputStream());
            oos.writeObject(platosManager.getPlatos());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
