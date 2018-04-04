package view;

import model.MainViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SelectorView extends JPanel {

    private JButton jbGestionMesas;
    private JButton jbGestionCarta;
    private JButton jbGestionPedidos;
    private JButton jbTop5;

    public SelectorView() {

        setLayout(new GridLayout(1,4));

        jbGestionMesas = new JButton();
        jbGestionMesas.setFocusPainted(false);
        jbGestionMesas.setBackground(Color.GRAY);

        jbGestionCarta = new JButton();
        jbGestionCarta.setFocusPainted(false);
        jbGestionCarta.setBackground(Color.GRAY);

        jbGestionPedidos = new JButton();
        jbGestionPedidos.setFocusPainted(false);
        jbGestionPedidos.setBackground(Color.GRAY);

        jbTop5 = new JButton();
        jbTop5.setFocusPainted(false);
        jbTop5.setBackground(Color.GRAY);

        add(jbGestionMesas);
        add(jbGestionCarta);
        add(jbGestionPedidos);
        add(jbTop5);
    }

    public void initView(MainViewModel mvm) {
        jbGestionMesas.setText(mvm.getGestionMesas());
        jbGestionCarta.setText(mvm.getGestionCarta());
        jbGestionPedidos.setText(mvm.getGestionPedidos());
        jbTop5.setText(mvm.getTop5());
    }

    public void registerControllers(ActionListener selectorViewListener) {

        jbGestionMesas.addActionListener(selectorViewListener);
        jbGestionMesas.setActionCommand("Mesas");
        jbGestionCarta.addActionListener(selectorViewListener);
        jbGestionCarta.setActionCommand("Carta");
        jbGestionPedidos.addActionListener(selectorViewListener);
        jbGestionPedidos.setActionCommand("Pedidos");
        jbTop5.addActionListener(selectorViewListener);
        jbTop5.setActionCommand("Top 5");
    }

    public void setSelectedButton(String buttonId) {

        if (jbGestionMesas.getText().equals(buttonId)) {
            jbGestionMesas.setBackground(Color.ORANGE);
        } else {
            jbGestionMesas.setBackground(Color.GRAY);
        }

        if (jbGestionCarta.getText().equals(buttonId)) {
            jbGestionCarta.setBackground(Color.ORANGE);
        } else {
            jbGestionCarta.setBackground(Color.GRAY);
        }

        if (jbGestionPedidos.getText().equals(buttonId)) {
            jbGestionPedidos.setBackground(Color.ORANGE);
        } else {
            jbGestionPedidos.setBackground(Color.GRAY);
        }

        if (jbTop5.getText().equals(buttonId)) {
            jbTop5.setBackground(Color.ORANGE);
        } else {
            jbTop5.setBackground(Color.GRAY);
        }

    }
}
