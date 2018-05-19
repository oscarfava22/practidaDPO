package servidor.controller;

import servidor.model.Pedido;
import servidor.model.PedidosManager;
import servidor.network.ReservaServer;
import servidor.view.CustomLabel;
import servidor.view.MainView;
import servidor.view.PlatosView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

/**
 * Controlador de la vista de los platos pendientes del menu de Pedidos.
 * Cumple la funcion de poder seleccionar el "id" del plato seleccionado para ser servido.
 */
public class PlatosPendientesController implements MouseInputListener {

    private MainView mainView;
    private PlatosView platosView;
    private PedidosManager pedidosManager;
    private String selectedItemId = "";
    private ReservaServer reservaServer;

    /**
     * Constructor que inicializa las variables del controlador.
     * @param mainView vista principal del programa.
     * @param platosView vista de los platos pendientes.
     * @param pedidosManager gestor de pedidos.
     */
    public PlatosPendientesController(MainView mainView, PlatosView platosView, PedidosManager pedidosManager) {
        this.mainView = mainView;
        this.platosView = platosView;
        this.pedidosManager = pedidosManager;
    }

    /**
     * Permite registrar el servidor de Reserva para poder enviar mensajes de plato servido al cliente correspondiente.
     * @param reservaServer
     */
    public void registerServer(ReservaServer reservaServer) {
        this.reservaServer = reservaServer;
    }

    /**
     * Permite servir platos al cliente correspondiente cuando se seleccione algun plato de la lista de platos
     * pendiente y se aprete el boton "Servir".
     * Este proceso se realiza seleccionando el id del "CustomLabel" apretado refereente a cada plato y se selecciona
     * el nombre del cliente al cual enviar el mensaje accediendo a la columna 1 de la fila del pedido seleccionado.
     * @param e evento de Mouse detectado.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().getClass().equals(CustomLabel.class)) {
            CustomLabel cl = (CustomLabel) e.getSource();
            selectedItemId = cl.getId();
            platosView.setSelectedPlatoState(cl.getId(), true);
            mainView.getGestionPedidosView().setJbServeState(true);
        }

        if (e.getSource().getClass().equals(JButton.class)) {

            Pedido pedido = pedidosManager.getPedidoByReservaId((long)mainView.getGestionPedidosView().
                                                                getJtPedidos().getModel().
                                                                getValueAt(mainView.getSelectedRow(),0));

            //Servimos el plato internamente, pasa de la lista de pendientes a procesados
            pedido.servirPlato(Long.parseLong(selectedItemId));

            //Se envia el mensaje de servir plato al cliente correspondiente
            reservaServer.sendServirPlatoToClient(Long.parseLong(selectedItemId),
                                                 (String)mainView.getGestionPedidosView().
                                                         getJtPedidos().getModel().
                                                         getValueAt(mainView.getSelectedRow(), 1));

            //Se actualizan los paneles de los platos despues de servir un plato
            mainView.getGestionPedidosView().initPlatosPendientesView(pedido.getPlatosPendientes().getPlatos());
            mainView.getGestionPedidosView().registerPlatosPendientesController(this);
            mainView.getGestionPedidosView().initPlatosProcesadosView(pedido.getPlatosProcesados().getPlatos());

            //Se actualiza el numero totol de platos
            mainView.getGestionPedidosView().getJtPedidos().getModel().setValueAt(pedido.getTotalPlatos(),
                                                                                  mainView.getSelectedRow(),
                                                                                 4);

            //Se atualiza el numero de platos pendientes por servir
            mainView.getGestionPedidosView().getJtPedidos().getModel().setValueAt(pedido.getPlatosPendientes().
                                                                                  getPlatos().size(),
                                                                                  mainView.getSelectedRow(),
                                                                                 5);
            //Se desactiva el boton de servir hasta que no s vuelva a seleccionar un plato
            mainView.getGestionPedidosView().setJbServeState(false);

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
