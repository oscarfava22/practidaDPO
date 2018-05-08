package servidor.network;

import Network.Reserva.ReservaRequest;
import servidor.model.PlatosManager;
import servidor.model.ReservasManager;
import servidor.view.MainView;

import java.io.*;
import java.net.Socket;

public class EntryDedicatedServer extends Thread{

    private EntryServer entryServer;
    private Socket entryClientSocket;
    private MainView mainView;
    private PlatosManager platosManager;
    private ReservasManager reservasManager;
    private boolean isRunning;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;


    public EntryDedicatedServer(EntryServer entryServer, Socket entryClientSocket, MainView mainView, PlatosManager platosManager, ReservasManager reservasManager) {
        this.entryServer = entryServer;
        this.entryClientSocket = entryClientSocket;
        this.mainView = mainView;
        this.platosManager = platosManager;
        this.reservasManager = reservasManager;
        isRunning = false;
    }

    @Override
    public void run() {

        try {
            isRunning = true;

            while (isRunning) {
                ois = new ObjectInputStream(entryClientSocket.getInputStream());
                oos = new ObjectOutputStream(entryClientSocket.getOutputStream());
                ReservaRequest reservaRequest = (ReservaRequest) ois.readObject();
                oos.writeObject(reservasManager.verifyRequest(reservaRequest));
            }

        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println("An Entry Client Disconnected from Server");
            entryServer.removeDedicatedServer(this);
            mainView.setEntryServerStatus(false);

        }
        finally {
            try {
                ois.close();
            } catch (IOException e) {}
            try {
                oos.close();
            } catch (IOException e) {}
            try {
                entryClientSocket.close();
            } catch (IOException e) {}
        }
    }

    public void updateMessageToClient() {

        /*try {
            dos.writeBoolean(true);
            dos.writeUTF("123");

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


}
