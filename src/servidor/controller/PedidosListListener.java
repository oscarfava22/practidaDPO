package servidor.controller;

import servidor.model.Pedido;
import servidor.model.PedidosManager;
import servidor.view.MainView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 */
public class PedidosListListener implements ListSelectionListener {

    private MainView mainView;
    private PedidosManager pedidosManager;
    private boolean selected = true;
    private long last_value = -1;
    private PlatosPendientesController platosPendientesController;
    private PlatosProcesadosListener platosProcesadosListener;

    /**
     *
     * @param mainView
     * @param pedidosManager
     * @param platosPendientesController
     * @param platosProcesadosListener
     */
    public PedidosListListener(MainView mainView, PedidosManager pedidosManager,
                               PlatosPendientesController platosPendientesController,
                               PlatosProcesadosListener platosProcesadosListener) {
        this.mainView = mainView;
        this.pedidosManager = pedidosManager;
        this.platosPendientesController = platosPendientesController;
        this.platosProcesadosListener = platosProcesadosListener;
    }

    /**
     *
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {

        long val = (long)mainView.getGestionPedidosView().getJtPedidos().getModel().getValueAt(mainView.getSelectedRow(),
                                                                                              0);
        if (last_value !=  val) {

            last_value = val;
            Pedido pedido =  pedidosManager.getPedidoByReservaId(val);

            mainView.getGestionPedidosView().initPlatosPendientesView(pedido.getPlatosPendientes().getPlatos());
            mainView.getGestionPedidosView().registerPlatosPendientesController(platosPendientesController);
            mainView.getGestionPedidosView().initPlatosProcesadosView(pedido.getPlatosProcesados().getPlatos());
            mainView.getGestionPedidosView().registerPlatosProcesadosController(platosProcesadosListener);
        }
    }

}
