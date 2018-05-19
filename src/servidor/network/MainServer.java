package servidor.network;

import servidor.controller.GestionCartaViewListener;
import servidor.controller.PedidosListListener;
import servidor.model.PedidosManager;
import servidor.model.PlatosManager;
import servidor.model.ReservasManager;
import servidor.view.MainView;

/**
 *Servidor Principal que inicializa el servidor Entry y el servidor Reserva.
 */
public class MainServer {

    private EntryServer entryServer;
    private ReservaServer reservaServer;

    /**
     * Constructor que prepara los servidores para ser inicializados.
     * @param mainView vista principal del programa.
     * @param platosManager gestor de plator.
     * @param reservasManager gestor de reservas.
     * @param pedidosManager gestor de pedidos.
     * @param pedidosListListener controlador de la lista de pedidos.
     * @param gestionCartaViewListener controlador de la carta.
     */
    public MainServer(MainView mainView, PlatosManager platosManager, ReservasManager reservasManager,
                      PedidosManager pedidosManager, PedidosListListener pedidosListListener,
                      GestionCartaViewListener gestionCartaViewListener) {

        entryServer = new EntryServer(mainView, reservasManager);
        reservaServer = new ReservaServer(mainView, platosManager, reservasManager, pedidosManager, pedidosListListener,
                                          gestionCartaViewListener);
    }

    /**
     * Método para inicializar la ejecucion de los servidores en Threads independientes.
     */
    public void initServers() {
        entryServer.start();
        reservaServer.start();
    }

    /**
     *
     * @return el servidor "Reserva" encargado de gestionar la comunicación con los clientes "Reserva".
     */
    public ReservaServer getReservaServer() {
        return reservaServer;
    }

}
