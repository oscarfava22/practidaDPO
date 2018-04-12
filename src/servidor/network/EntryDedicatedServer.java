package servidor.network;

import servidor.model.PlatosManager;

import java.io.*;
import java.net.Socket;

public class EntryDedicatedServer extends Thread{

    private EntryServer entryServer;
    private Socket entryClientSocket;
    private PlatosManager platosManager;
    private boolean isRunning;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private DataInputStream dis;
    private DataOutputStream dos;

    public EntryDedicatedServer(EntryServer entryServer, Socket entryClientSocket, PlatosManager platosManager) {
        this.entryServer = entryServer;
        this.entryClientSocket = entryClientSocket;
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

                int num_tables = dis.readByte();
                System.out.println(num_tables);

                String string2 = dis.readUTF();
                System.out.println(string2);

                Object object = ois.readObject();
                System.out.println(object.toString());

                dos.writeBoolean(true);
                dos.writeUTF("123");

            }


        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println("An Entry Client Disconnected from Server");
            entryServer.removeDedicatedServer(this);
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


}
