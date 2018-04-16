package servidor.controller;

import servidor.model.LoginModel;
import servidor.view.MainView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class SelectorViewListener implements MouseInputListener {

    private MainView mainView;
    private LoginModel loginModel;

    public SelectorViewListener (MainView mainView, LoginModel loginModel) {
        this.mainView = mainView;
        this.loginModel = loginModel;
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().getClass().equals(JButton.class)) {

            JButton jb = (JButton) e.getSource();

            mainView.setSVSelectedButton(jb.getText());
            mainView.setGestionView(jb.getText());
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
        if (e.getSource().getClass().equals(JButton.class)) {

            JButton jb = (JButton) e.getSource();

            mainView.setSVFocusedButton(jb.getText(), true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().getClass().equals(JButton.class)) {

            JButton jb = (JButton) e.getSource();

            mainView.setSVFocusedButton(jb.getText(), false);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
