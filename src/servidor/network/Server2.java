package servidor.network;

import servidor.model.PlatosManager;
import servidor.view.MainView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server2 extends Thread {

    private ServerSocket serverSocket;
    private boolean isRunning;
    private LinkedList<DedicatedServer2> dedicatedServers;
    private MainView mainView;
    //private WaitingListManager waitingListManager;
    private PlatosManager platosManager;

    public Server2(MainView mainView, PlatosManager platosManager) {

        try {
            serverSocket = new ServerSocket(Network.ENTRY_SERVER_PORT);
            isRunning = false;
            dedicatedServers = new LinkedList<>();
            this.mainView = mainView;
            this.platosManager = platosManager;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        isRunning = true;

        while (isRunning) {
            try {
                System.out.println("Waiting for a client...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");

                DedicatedServer2 clientServer = new DedicatedServer2(this, clientSocket, platosManager);
                dedicatedServers.add(clientServer);
                clientServer.start();

                updateConnectedDevices();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateConnectedDevices(){
        mainView.setConnectedDevices(dedicatedServers.size());
    }

    private void updateMainView() {

        /*mainView.updateWaitingList(waitingListManager.getAllClients());

        if (waitingListManager.getClientsCount() > 0) {
            mainView.updateUserProduct(waitingListManager.getFirstClient().getUserName(),
                    waitingListManager.getFirstClient().getProductName());
        } else {
            mainView.updateUserProduct("","");
        }*/
    }

    public void sendBroadcast() {

        updateMainView();

        for(DedicatedServer2 dServer: dedicatedServers) {
            dServer.updateMessageToClient();
        }
    }

    public void removeDedicatedServer(DedicatedServer2 dServer) {
        dedicatedServers.remove(dServer);
    }
}
