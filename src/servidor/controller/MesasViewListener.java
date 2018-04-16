package servidor.controller;

import servidor.model.MesasManager;
import servidor.view.MainView;

import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class MesasViewListener implements MouseInputListener{

    private MainView mainView;
    private MesasManager mesasManager;
    private MesasOptionsViewListener mesasOptionsViewListener;

    public MesasViewListener(MainView mainView, MesasManager mesasManager, MesasOptionsViewListener mesasOptionsViewListener) {
        this.mainView = mainView;
        this.mesasManager = mesasManager;
        this.mesasOptionsViewListener = mesasOptionsViewListener;
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
