package servidor.controller;

import com.google.gson.JsonArray;
import servidor.model.Mesa;
import servidor.model.MesasManager;
import servidor.view.GestionMesasView;
import servidor.view.MainView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class GestionMesasViewListener implements MouseInputListener{

    private MainView mainView;
    private MesasManager mesasManager;

    //Constructor
    public GestionMesasViewListener(MainView mainView, MesasManager mesasManager) {
        this.mainView = mainView;
        this.mesasManager = mesasManager;
    }

    //Getters & Setters
    public MainView getMainView() {
        return mainView;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public MesasManager getMesasManager() {
        return mesasManager;
    }

    public void setMesasManager(MesasManager mesasManager) {
        this.mesasManager = mesasManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
