package servidor.controller;

import javafx.collections.ListChangeListener;
import servidor.model.PedidosManager;
import servidor.model.PlatosManager;
import servidor.view.MainView;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Arrays;

public class PedidosListListener implements ListSelectionListener {

    private MainView mainView;
    private PedidosManager pedidosManager;
    private boolean selected = true;
    private int last_value = -1;
    private PlatosManager platosManager;

    public PedidosListListener(MainView mainView, PedidosManager pedidosManager, PlatosManager platosManager) {
        this.mainView = mainView;
        this.pedidosManager = pedidosManager;
        this.platosManager = platosManager;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        if (last_value != mainView.getSelectedRow() ) {
            System.out.println("Click");
            last_value = mainView.getSelectedRow();
            mainView.getGestionPedidosView().initPlatosPendientesView(platosManager.getPlatos());
            mainView.getGestionPedidosView().registerPlatosPendientesController(new PlatosPendientesController(mainView.getGestionPedidosView().getPlatosPendientes()));
        }

    }

}
