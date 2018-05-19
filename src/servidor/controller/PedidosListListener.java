package servidor.controller;

import servidor.model.Pedido;
import servidor.model.PedidosManager;
import servidor.view.MainView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Controlador de la vista de pedidos. Cumple la funcion de mostarar los platos pendientes y procesados de cada cliente
 * en funcion del pedido que se seleccione en la lista de pedidos.
 */
public class PedidosListListener implements ListSelectionListener {

    private MainView mainView;
    private PedidosManager pedidosManager;
    private boolean selected = true;
    private long last_value = -1;
    private PlatosPendientesController platosPendientesController;

    /**
     *
     * @param mainView vista principal del programa.
     * @param pedidosManager gestor de pedidos.
     * @param platosPendientesController controlador de platos pendientes.
     */
    public PedidosListListener(MainView mainView, PedidosManager pedidosManager,
                               PlatosPendientesController platosPendientesController) {
        this.mainView = mainView;
        this.pedidosManager = pedidosManager;
        this.platosPendientesController = platosPendientesController;
    }

    /**
     * Permite mostrar los platos pendientes y procesados de cada cliente cogiendo el identificador de cada reserva
     * que se encuentra en la columna 0 de cada fila seleccionada, y busca ese pedido en el gestor de pedidos, para
     * posteriormente mostrar los datos en pantalla.
     * @param e evento detectado.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {

        long val = (long)mainView.getGestionPedidosView().getJtPedidos().getModel().getValueAt(mainView.getSelectedRow(),
                                                                                              0);
        if (last_value !=  val) {

            last_value = val;

            //Se obtiene el pedido correspondiente al id de la reserva seleccionada en la lista de pedidos
            Pedido pedido =  pedidosManager.getPedidoByReservaId(val);

            //Se actualizan los paneles de los platos mostrando los platos pendiente y procesados del pedido
            mainView.getGestionPedidosView().initPlatosPendientesView(pedido.getPlatosPendientes().getPlatos());
            mainView.getGestionPedidosView().registerPlatosPendientesController(platosPendientesController);
            mainView.getGestionPedidosView().initPlatosProcesadosView(pedido.getPlatosProcesados().getPlatos());
        }
    }

}
