package servidor.network;

import Network.Reserva.ReservaRequest;
import servidor.model.ReservasManager;
import servidor.view.MainView;

import java.io.*;
import java.net.Socket;

/**
 * Servidor dedicado para cada Entry Client que se conecte, permite gestionar de forma independiente cada cliente,
 * ofreciendo una comunicacion en paralelo con todos los clientes.
 */
public class EntryDedicatedServer extends Thread{

    private EntryServer entryServer;
    private Socket entryClientSocket;
    private MainView mainView;
    private ReservasManager reservasManager;
    private boolean isRunning;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    /**
     * Constructor principal que prepara todas las variables que seran utilizadas por el servidor dedicado.
     * @param entryServer Entry Server para permitir que este servidor dedicado notifique al principal.
     * @param entryClientSocket socket que ha abierto el "Entry Server" para este servidor dedicado.
     * @param mainView vista principal del programa.
     * @param reservasManager gestor de reservas.
     */
    public EntryDedicatedServer(EntryServer entryServer, Socket entryClientSocket, MainView mainView, ReservasManager reservasManager) {
        this.entryServer = entryServer;
        this.entryClientSocket = entryClientSocket;
        this.mainView = mainView;
        this.reservasManager = reservasManager;
        isRunning = false;
    }

    /**
     * Metodo que inicializa el servidor dedicado, que se mantendra en ejecuccion constantemente mientras exista la
     * conexion con el cliente Entry.
     * Este servidor esta constantemente esperando a leer un objeto del tipo (ReservaRequest), que contendra el
     * nombre de la persona que desea realizar la reserva y el numero de comensales para la mesa solicitada, una vez
     * se reciba esta solicitud, se verificara mediante la base de datos si existe una mesa disponible para la fecha
     * solicitada.
     */
    @Override
    public void run() {

        try {
            ois = new ObjectInputStream(entryClientSocket.getInputStream());
            oos = new ObjectOutputStream(entryClientSocket.getOutputStream());
            isRunning = true;

            while (isRunning) {
                ReservaRequest reservaRequest = (ReservaRequest) ois.readObject();
                oos.writeObject(reservasManager.verifyRequest(reservaRequest));
            }

        } catch (IOException | ClassNotFoundException e) {
            entryServer.removeDedicatedServer(this);
            mainView.setEntryServerStatus(entryServer.getDedicatedServersCount());
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
}
