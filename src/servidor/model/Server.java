package servidor.model;

import servidor.view.MainView;

import java.net.ServerSocket;
import java.util.List;

public class Server {
    private boolean isOn;
    private ServerSocket sSocket;
    private List<DedicatedServer> dServers;
    private ServerData data;
}
