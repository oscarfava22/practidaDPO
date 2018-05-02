package servidor.controller;

import javafx.collections.ListChangeListener;
import servidor.model.PedidosManager;
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
    private int last_value = 0;

    public PedidosListListener(MainView mainView, PedidosManager pedidosManager) {
        this.mainView = mainView;
        this.pedidosManager = pedidosManager;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        if (last_value != mainView.getSelectedRow() ) {
            System.out.println("Click");
            last_value = mainView.getSelectedRow();

        }

    }

}
