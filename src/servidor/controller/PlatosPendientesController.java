package servidor.controller;

import servidor.model.Pedido;
import servidor.model.PedidosManager;
import servidor.model.Plato;
import servidor.model.PlatosManager;
import servidor.view.CustomLabel;
import servidor.view.MainView;
import servidor.view.PlatoView;
import servidor.view.PlatosView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class PlatosPendientesController implements MouseInputListener {

    private MainView mainView;
    private PlatosView platosView;
    private PedidosManager pedidosManager;
    private String selectedItemId = "-1";

    public PlatosPendientesController(MainView mainView, PlatosView platosView, PedidosManager pedidosManager) {
        this.mainView = mainView;
        this.platosView = platosView;
        this.pedidosManager = pedidosManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().getClass().equals(CustomLabel.class)) {
            CustomLabel cl = (CustomLabel) e.getSource();
            System.out.println(cl.getId());
            selectedItemId = cl.getId();
            platosView.setSelectedPlatoState(cl.getId(), true);
        }

        if (e.getSource().getClass().equals(JButton.class)) {



            JButton jb = (JButton) e.getSource();
            System.out.println("SELECTED ITEM: " + selectedItemId);
            System.out.println(jb.getText() + " " + selectedItemId + " " + mainView.getGestionPedidosView().getJtPedidos().getModel().getValueAt(mainView.getSelectedRow(), 0));
            Pedido pedido = pedidosManager.getPedidoByReservaId((long)mainView.getGestionPedidosView().getJtPedidos().getModel().getValueAt(mainView.getSelectedRow(), 0));
            pedido.servirPlato(Long.parseLong(selectedItemId));
            //mainView.getGestionPedidosView().getPlatosPendientes()

            System.out.println("Platos procesados size: " + pedidosManager.getPedidoByReservaId((long)mainView.getGestionPedidosView().getJtPedidos().getModel().getValueAt(mainView.getSelectedRow(), 0)).getPlatosProcesados().getPlatos().size());

            platosView.initPlatosView(pedido.getPlatosPendientes().getPlatos());
            platosView.registerControllers(this);
            mainView.getGestionPedidosView().initPlatosProcesadosView(pedido.getPlatosProcesados().getPlatos());
            //mainView.getGestionPedidosView().registerPlatosProcesadosController(new PlatosProcesadosListener(mainView.getGestionPedidosView().getPlatosProcesados()));

            //platosView.deletePlato(selectedItemId);
            //Plato plato = pedidosManager.get

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
