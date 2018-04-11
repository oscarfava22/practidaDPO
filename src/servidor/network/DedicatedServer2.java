package servidor.network;

import servidor.model.Plato;
import servidor.model.PlatosManager;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class DedicatedServer2 extends Thread {

    private final Server2 server;
    private boolean isRunning;

    private Socket clientSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    //private WaitingListManager waitingListManager;
    private PlatosManager platosManager;


    public DedicatedServer2(Server2 server, Socket clientSocket, PlatosManager platosManager) {
        this.server = server;
        isRunning = false;
        this.clientSocket = clientSocket;
        this.platosManager = platosManager;
    }

    @Override
    public void run() {

        try {
            ois = new ObjectInputStream(clientSocket.getInputStream());
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            isRunning = true;

            System.out.println("New Client Connected to Server");
            server.sendBroadcast();

            while (isRunning) {

                //Plato plato = (Plato) ois.readObject();
                LinkedList<Plato> reserva = (LinkedList<Plato>) ois.readObject();
                
                /*System.out.println(client.toString());
                waitingListManager.addClient(client);
                server.sendBroadcast();*/
            }


        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println("A client Disconnected from Server");
            server.removeDedicatedServer(this);
            server.updateConnectedDevices();

        } finally {
            try {
                ois.close();
            } catch (IOException e) {}
            try {
                oos.close();
            } catch (IOException e) {}
            try {
                clientSocket.close();
            } catch (IOException e) {}
        }
    }

    public void updateMessageToClient() {
        try {
            oos.writeObject(platosManager.getPlatos());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
