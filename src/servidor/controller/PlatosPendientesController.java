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
 *
 */
public class PlatosPendientesController implements MouseInputListener {

    private MainView mainView;
    private PlatosView platosView;
    private PedidosManager pedidosManager;
    private String selectedItemId = "";
    private ReservaServer reservaServer;

    /**
     *
     * @param mainView
     * @param platosView
     * @param pedidosManager
     */
    public PlatosPendientesController(MainView mainView, PlatosView platosView, PedidosManager pedidosManager) {
        this.mainView = mainView;
        this.platosView = platosView;
        this.pedidosManager = pedidosManager;
    }

    /**
     *
     * @param reservaServer
     */
    public void registerServer(ReservaServer reservaServer) {
        this.reservaServer = reservaServer;
    }

    /**
     *
     * @param e
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
            pedido.servirPlato(Long.parseLong(selectedItemId));

            reservaServer.sendServirPlatoToClient(Long.parseLong(selectedItemId),
                                                 (String)mainView.getGestionPedidosView().
                                                         getJtPedidos().getModel().
                                                         getValueAt(mainView.getSelectedRow(), 1));

            mainView.getGestionPedidosView().initPlatosPendientesView(pedido.getPlatosPendientes().getPlatos());
            mainView.getGestionPedidosView().registerPlatosPendientesController(this);
            mainView.getGestionPedidosView().initPlatosProcesadosView(pedido.getPlatosProcesados().getPlatos());
            //mainView.getGestionPedidosView().registerPlatosProcesadosController(new PlatosProcesadosListener(mainView.getGestionPedidosView().getPlatosProcesados()));

            mainView.getGestionPedidosView().getJtPedidos().getModel().setValueAt(pedido.getTotalPlatos(),
                                                                                  mainView.getSelectedRow(),
                                                                                 4);

            mainView.getGestionPedidosView().getJtPedidos().getModel().setValueAt(pedido.getPlatosPendientes().
                                                                                  getPlatos().size(),
                                                                                  mainView.getSelectedRow(),
                                                                                 5);

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
