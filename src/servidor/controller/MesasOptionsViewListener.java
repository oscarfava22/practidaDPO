package servidor.controller;

import servidor.model.MesasManager;
import servidor.view.GestionMesasView;
import servidor.view.MainView;

import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class MesasOptionsViewListener implements ActionListener {

    private MainView mainView;
    private MesasManager mesasManager;

    public MesasOptionsViewListener(MainView mainView, MesasManager mesasManager){
        this.mainView = mainView;
        this.mesasManager = mesasManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case GestionMesasView.AÑADIR_MESA_TAG:
                break;
            case GestionMesasView.ELIMINAR_MESA_TAG:
                break;
        }
    }
}
