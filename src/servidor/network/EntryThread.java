package servidor.network;

import java.io.IOException;
import java.net.ServerSocket;

public class EntryThread extends Thread {

    private ServerSocket entrySocket;


    public EntryThread() {

        try{
            entrySocket = new ServerSocket(Network.ENTRY_SERVER_PORT);
            } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
