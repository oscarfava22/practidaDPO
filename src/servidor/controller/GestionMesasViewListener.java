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
import java.util.LinkedList;

public class GestionMesasViewListener implements ActionListener {

    private MainView mainView;
    private MesasManager mesasManager;

    //Constructor
    public GestionMesasViewListener(MainView mainView, MesasManager mesasManager) {
        this.mainView = mainView;
        this.mesasManager = mesasManager;
        actualitzaView();
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
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){

        }
    }

    public void actualitzaView(){
        LinkedList<Mesa> mesas = getMesasManager().getMesas();
        //GestionMesasView view = mainView.getGestionMesasView();
        //view.setMesasScrollablePanel(mesas);
        //mainView.setGestionbMesasView(view);

    }
}
