package servidor.view;

import servidor.controller.SelectorViewListener;
import servidor.model.Constants;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class SelectorView extends JPanel {

    private JButton jbGestionMesas;
    private JButton jbGestionCarta;
    private JButton jbGestionPedidos;
    private JButton jbTop5;

    /**
     *
     */
    public SelectorView() {

        setLayout(new GridLayout(1,4));

        jbGestionMesas = new JButton();
        jbGestionMesas.setFocusPainted(false);
        jbGestionMesas.setForeground(Color.BLACK);
        jbGestionMesas.setBackground(Color.GRAY);
        jbGestionMesas.setBorderPainted(false);

        jbGestionCarta = new JButton();
        jbGestionCarta.setFocusPainted(false);
        jbGestionCarta.setForeground(Color.BLACK);
        jbGestionCarta.setBackground(Color.GRAY);
        jbGestionCarta.setBorderPainted(false);

        jbGestionPedidos = new JButton();
        jbGestionPedidos.setFocusPainted(false);
        jbGestionPedidos.setForeground(Color.BLACK);
        jbGestionPedidos.setBackground(Color.GRAY);
        jbGestionPedidos.setBorderPainted(false);

        jbTop5 = new JButton();
        jbTop5.setFocusPainted(false);
        jbTop5.setForeground(Color.BLACK);
        jbTop5.setBackground(Color.GRAY);
        jbTop5.setBorderPainted(false);

        add(jbGestionMesas);
        add(jbGestionCarta);
        add(jbGestionPedidos);
        add(jbTop5);
    }

    /**
     *
     */
    public void initView() {
        jbGestionMesas.setText(Constants.gestionMesas);
        jbGestionCarta.setText(Constants.gestionCarta);
        jbGestionPedidos.setText(Constants.gestionPedidos);
        jbTop5.setText(Constants.top5);
    }

    /**
     *
     * @param selectorViewListener
     */
    public void registerControllers(SelectorViewListener selectorViewListener) {
        jbGestionMesas.addMouseListener(selectorViewListener);
        jbGestionMesas.addMouseMotionListener(selectorViewListener);
        jbGestionCarta.addMouseListener(selectorViewListener);
        jbGestionCarta.addMouseMotionListener(selectorViewListener);
        jbGestionPedidos.addMouseListener(selectorViewListener);
        jbGestionPedidos.addMouseMotionListener(selectorViewListener);
        jbTop5.addMouseListener(selectorViewListener);
        jbTop5.addMouseMotionListener(selectorViewListener);
    }

    /**
     *
     * @param buttonId
     */
    public void setSelectedButton(String buttonId) {

        if (jbGestionMesas.getText().equals(buttonId)) {
            jbGestionMesas.setForeground(Color.BLACK);
            jbGestionMesas.setBackground(Color.PINK);
            jbGestionMesas.setSelected(true);
        } else {
            jbGestionMesas.setBackground(Color.GRAY);
            jbGestionMesas.setSelected(false);
        }

        if (jbGestionCarta.getText().equals(buttonId)) {
            jbGestionCarta.setForeground(Color.BLACK);
            jbGestionCarta.setBackground(Color.ORANGE);
            jbGestionCarta.setSelected(true);
        } else {
            jbGestionCarta.setBackground(Color.GRAY);
            jbGestionCarta.setSelected(false);

        }

        if (jbGestionPedidos.getText().equals(buttonId)) {
            jbGestionPedidos.setForeground(Color.BLACK);
            jbGestionPedidos.setBackground(Color.CYAN);
            jbGestionPedidos.setSelected(true);
        } else {
            jbGestionPedidos.setBackground(Color.GRAY);
            jbGestionPedidos.setSelected(false);

        }

        if (jbTop5.getText().equals(buttonId)) {
            jbTop5.setForeground(Color.BLACK);
            jbTop5.setBackground(Color.GREEN);
            jbTop5.setSelected(true);
        } else {
            jbTop5.setBackground(Color.GRAY);
            jbTop5.setSelected(false);
        }
    }

    /**
     *
     * @param buttonId
     * @param state
     */
    public void setFocusedButton(String buttonId, boolean state) {

        if (jbGestionMesas.getText().equals(buttonId)) {
            if(state) {
                if (!jbGestionMesas.isSelected()) {
                    jbGestionMesas.setForeground(Color.PINK);
                } else {
                    jbGestionMesas.setForeground(Color.BLACK);
                }
            } else {
                jbGestionMesas.setForeground(Color.BLACK);
            }
        }

        if (jbGestionCarta.getText().equals(buttonId)) {
            if(state) {
                if (!jbGestionCarta.isSelected()) {
                    jbGestionCarta.setForeground(Color.ORANGE);
                } else {
                    jbGestionCarta.setForeground(Color.BLACK);
                }
            } else {
                jbGestionCarta.setForeground(Color.BLACK);
            }
        }

        if (jbGestionPedidos.getText().equals(buttonId)) {
            if(state) {
                if (!jbGestionPedidos.isSelected()) {
                    jbGestionPedidos.setForeground(Color.CYAN);
                } else {
                    jbGestionPedidos.setForeground(Color.BLACK);
                }
            } else {
                jbGestionPedidos.setForeground(Color.BLACK);
            }
        }

        if (jbTop5.getText().equals(buttonId)) {
            if(state) {
                if (!jbTop5.isSelected()) {
                    jbTop5.setForeground(Color.GREEN);
                } else {
                    jbTop5.setForeground(Color.BLACK);
                }
            } else {
                jbTop5.setForeground(Color.BLACK);
            }
        }

    }
}
