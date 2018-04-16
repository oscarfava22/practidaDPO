package servidor.network;

import servidor.model.PlatosManager;
import servidor.model.ReservasManager;
import servidor.view.MainView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class EntryServer extends Thread {

    private MainServer mainServer;
    private ServerSocket entryServerSocket;
    private boolean isRunning;

    private MainView mainView;
    private PlatosManager platosManager;
    private ReservasManager reservasManager;

    private LinkedList<EntryDedicatedServer> entryDedicatedServers;

    public EntryServer(MainServer mainServer, MainView mainView, PlatosManager platosManager, ReservasManager reservasManager) {

        try{
            entryServerSocket = new ServerSocket(Network.ENTRY_SERVER_PORT);

            this.mainServer = mainServer;
            this.mainView = mainView;
            this.platosManager = platosManager;
            this.reservasManager = reservasManager;
            isRunning = false;

            entryDedicatedServers = new LinkedList<>();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

        isRunning = true;

        while(isRunning) {

            try {
                System.out.println("Waiting for an Entry Client...");
                Socket entryClientSocket = entryServerSocket.accept();
                System.out.println("Entry Client connected");

                EntryDedicatedServer entryDedicatedServer = new EntryDedicatedServer(this, entryClientSocket, mainView, platosManager);

                entryDedicatedServers.add(entryDedicatedServer);
                entryDedicatedServer.start();

                mainView.setEntryServerStatus(true);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeDedicatedServer(EntryDedicatedServer entryDedicatedServer) {
        entryDedicatedServers.remove(entryDedicatedServer);
    }

    public void sendBroadcast() {

        for(EntryDedicatedServer eds : entryDedicatedServers) {
            eds.updateMessageToClient();
        }
    }

}
