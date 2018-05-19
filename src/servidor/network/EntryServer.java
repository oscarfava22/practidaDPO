package servidor.network;

import servidor.model.ReservasManager;
import servidor.view.MainView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Servidor que gestiona la comunicacion con todos los "EntryDedicatedServers".
 */
public class EntryServer extends Thread {

    private ServerSocket entryServerSocket;
    private boolean isRunning;
    private MainView mainView;
    private ReservasManager reservasManager;
    private LinkedList<EntryDedicatedServer> entryDedicatedServers;

    /**
     * Constructor que prepara el ServerSocket con el puerto establecido e inicializa las variables que se utilizaran
     * durante la ejecuccion del servidor.
     * @param mainView vista principal del programa.
     * @param reservasManager gestor de reservas.
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
     * Metodo que inicializa el servidor Entry, que estara continuamente esperando la conexion de nuevos clientes,
     * cuando detecte una conexion en el puerto establecido, procedera a ejecutar un nuevo Thread con un servidor
     * dedicado para gestionar independientemente cada conexion que se establezca, añadira el nuevo servidor dedicado
     * a una lista interna para poder gestionarlos todos.
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

                //Se actualiza el valor de entry clientes conectados en el "Status Bar" de la vista principal
                mainView.setEntryServerStatus(getDedicatedServersCount());

            } catch (IOException e) {
                e.printStackTrace();
                isRunning = false;
            }
        }
    }

    /**
     * Permite eliminar un servidor dedicado cuando se detecte que el cliente se ha desconectado.
     * @param entryDedicatedServer servidor dedicado que se quiere eliminar.
     */
    public void removeDedicatedServer(EntryDedicatedServer entryDedicatedServer) {
        entryDedicatedServers.remove(entryDedicatedServer);
    }

    /**
     * Permite conocer el numero actual de servidores dedicados en ejecuccion y por consiguiente el numero de clientes
     * conectados.
     * @return el numero actual de clientes Entry conectados.
     */
    public int getDedicatedServersCount() {
        return entryDedicatedServers.size();
    }

}
