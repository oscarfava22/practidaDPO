package servidor.network;

import Network.Reserva.ReservaRequest;
import Network.Reserva.ReservaResponse;
import servidor.model.PlatosManager;
import servidor.view.MainView;

import java.io.*;
import java.net.Socket;

public class EntryDedicatedServer extends Thread{

    private EntryServer entryServer;
    private Socket entryClientSocket;
    private MainView mainView;
    private PlatosManager platosManager;
    private boolean isRunning;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private DataInputStream dis;
    private DataOutputStream dos;

    public EntryDedicatedServer(EntryServer entryServer, Socket entryClientSocket, MainView mainView, PlatosManager platosManager) {
        this.entryServer = entryServer;
        this.entryClientSocket = entryClientSocket;
        this.mainView = mainView;
        this.platosManager = platosManager;
        isRunning = false;
    }

    @Override
    public void run() {

        try {
            ois = new ObjectInputStream(entryClientSocket.getInputStream());
            oos = new ObjectOutputStream(entryClientSocket.getOutputStream());
            dis = new DataInputStream(entryClientSocket.getInputStream());
            dos = new DataOutputStream(entryClientSocket.getOutputStream());

            isRunning = true;

            while (isRunning) {

                ReservaRequest rr = (ReservaRequest) ois.readObject();

                ReservaResponse response = new ReservaResponse("Hola",true);
                oos.writeObject(response);

                //int num_tables = (Integer) ois.readObject();
                //System.out.println(num_tables);

                //String string2 = (String)ois.readObject();
                //System.out.println(string2);

                //Object object = ois.readObject();
                //System.out.println(object.toString());

                //dos.writeInt(1);
                //dos.writeUTF("Hola mundo");
                //updateMessageToClient();

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
                dis.close();
            } catch (IOException e) {}
            try {
                dos.close();
            } catch (IOException e) {}
            try {
                entryClientSocket.close();
            } catch (IOException e) {}
        }
    }

    public void updateMessageToClient() {

        try {
            dos.writeBoolean(true);
            dos.writeUTF("123");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
