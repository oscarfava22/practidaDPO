package servidor.controller;

import com.google.gson.JsonArray;
import servidor.model.Mesa;
import servidor.model.MesasManager;
import servidor.view.GestionMesasView;
import servidor.view.MainView;
import servidor.view.MesaView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class GestionMesasViewListener implements MouseInputListener{

    /**
     * Atributos de la clase
     */
    private MainView mainView;

    /**
     * Construcotr de la clase
     * @param mainView
     */
    public GestionMesasViewListener(MainView mainView) {
        this.mainView = mainView;
    }

    //Getters i Setters
    /**
     * Getter
     * @return mainView
     */
    public MainView getMainView() {
        return mainView;
    }

    /**
     * Setter
     * @param mainView
     */
    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().getClass().equals(JButton.class)) {
            JButton jb = (JButton) e.getSource();
            System.out.println(jb.getText());
        }

        if (e.getSource().getClass().equals(JLabel.class)) {
            JLabel jl = (JLabel) e.getSource();
            System.out.println(jl.getText());
        }

        if (e.getSource().getClass().equals(MesaView.class)){
            MesaView mv = (MesaView) e.getSource();
            System.out.println(mv.getJlIdMesa().toString() + " - " + mv.getJlNumComensalsMax().toString());
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
