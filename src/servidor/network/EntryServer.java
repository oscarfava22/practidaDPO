package servidor.network;

import servidor.model.ReservasManager;
import servidor.view.MainView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 *
 */
public class EntryServer extends Thread {

    private ServerSocket entryServerSocket;
    private boolean isRunning;
    private MainView mainView;
    private ReservasManager reservasManager;
    private LinkedList<EntryDedicatedServer> entryDedicatedServers;

    /**
     *
     * @param mainView
     * @param reservasManager
     */
    public EntryServer(MainView mainView, ReservasManager reservasManager) {

        try{
            entryServerSocket = new ServerSocket(Network.ENTRY_SERVER_PORT);
            entryDedicatedServers = new LinkedList<>();
            this.mainView = mainView;
            this.reservasManager = reservasManager;
            isRunning = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    @Override
    public void run() {

        isRunning = true;

        while(isRunning) {

            try {
                Socket entryClientSocket = entryServerSocket.accept();

                EntryDedicatedServer entryDedicatedServer = new EntryDedicatedServer(this, entryClientSocket,
                                                                                     mainView, reservasManager);
                entryDedicatedServers.add(entryDedicatedServer);
                entryDedicatedServer.start();
                mainView.setEntryServerStatus(true);

            } catch (IOException e) {
                e.printStackTrace();
                isRunning = false;
            }
        }
    }

    /**
     *
     * @param entryDedicatedServer
     */
    public void removeDedicatedServer(EntryDedicatedServer entryDedicatedServer) {
        entryDedicatedServers.remove(entryDedicatedServer);
    }

}
