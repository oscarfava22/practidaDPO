package servidor.network;

import servidor.controller.GestionCartaViewListener;
import servidor.controller.PedidosListListener;
import servidor.model.PedidosManager;
import servidor.model.PlatosManager;
import servidor.model.ReservasManager;
import servidor.view.MainView;

/**
 *
 */
public class MainServer {

    private EntryServer entryServer;
    private ReservaServer reservaServer;

    /**
     *
     * @param mainView
     * @param platosManager
     * @param reservasManager
     * @param pedidosManager
     * @param pedidosListListener
     * @param gestionCartaViewListener
     */
    public MainServer(MainView mainView, PlatosManager platosManager, ReservasManager reservasManager,
                      PedidosManager pedidosManager, PedidosListListener pedidosListListener,
                      GestionCartaViewListener gestionCartaViewListener) {

        entryServer = new EntryServer(mainView, reservasManager);
        reservaServer = new ReservaServer(mainView, platosManager, reservasManager, pedidosManager, pedidosListListener,
                                          gestionCartaViewListener);
    }

    /**
     *
     */
    public void initServers() {
        entryServer.start();
        reservaServer.start();
    }

    /**
     *
     * @return
     */
    public ReservaServer getReservaServer() {
        return reservaServer;
    }

}
