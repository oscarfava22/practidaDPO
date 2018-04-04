package servidor.controller;

import servidor.view.MainView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class SettingsDialogViewListener implements MouseInputListener {

    private MainView mainView;

    public SettingsDialogViewListener(MainView mainView) {
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
