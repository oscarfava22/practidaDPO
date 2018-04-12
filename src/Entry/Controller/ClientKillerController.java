package Entry.Controller;

import Entry.Model.Network.Client;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Disconnects the client when closing window
 */
public class ClientKillerController extends WindowAdapter{

    /**
     * Client to disconnect
     */
    private Client client;

    /***
     * Creates a new client killer from a client
     * @param client the client to kill
     */
    public ClientKillerController(Client client) {
        this.client = client;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            client.disconnect();
        } catch (IOException e1) {
            System.err.println("Error closing client");
        }
    }
}
