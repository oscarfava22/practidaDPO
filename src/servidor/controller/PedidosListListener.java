package servidor.controller;

import servidor.model.Pedido;
import servidor.model.PedidosManager;
import servidor.model.PlatosManager;
import servidor.view.MainView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PedidosListListener implements ListSelectionListener {

    private MainView mainView;
    private PedidosManager pedidosManager;
    private boolean selected = true;
    private long last_value = -1;
    private PlatosManager platosManager;
    private PlatosPendientesController platosPendientesController;
    private PlatosProcesadosListener platosProcesadosListener;

    public PedidosListListener(MainView mainView, PedidosManager pedidosManager, PlatosManager platosManager,
                               PlatosPendientesController platosPendientesController,
                               PlatosProcesadosListener platosProcesadosListener) {
        this.mainView = mainView;
        this.pedidosManager = pedidosManager;
        this.platosManager = platosManager;
        this.platosPendientesController = platosPendientesController;
        this.platosProcesadosListener = platosProcesadosListener;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        if (last_value != (long)mainView.getGestionPedidosView().getJtPedidos().getModel().getValueAt(mainView.getSelectedRow(), 0) ) {
            System.out.println("Click id:" + (long)mainView.getGestionPedidosView().getJtPedidos().getModel().getValueAt(mainView.getSelectedRow(), 0));
            last_value = (long)mainView.getGestionPedidosView().getJtPedidos().getModel().getValueAt(mainView.getSelectedRow(), 0);

            Pedido pedido =  pedidosManager.getPedidoByReservaId((long)mainView.getGestionPedidosView().getJtPedidos().getModel().getValueAt(mainView.getSelectedRow(), 0));

            mainView.getGestionPedidosView().initPlatosPendientesView(pedido.getPlatosPendientes().getPlatos());
            mainView.getGestionPedidosView().registerPlatosPendientesController(platosPendientesController);
            mainView.getGestionPedidosView().initPlatosProcesadosView(pedido.getPlatosProcesados().getPlatos());
            mainView.getGestionPedidosView().registerPlatosProcesadosController(platosProcesadosListener);
        }

    }

}
