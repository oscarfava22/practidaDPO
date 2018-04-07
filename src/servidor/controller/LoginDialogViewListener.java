package servidor.controller;

import servidor.model.LoginModel;
import servidor.view.MainView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class LoginDialogViewListener implements MouseInputListener {

    private MainView mainView;
    private LoginModel loginModel;

    public LoginDialogViewListener(MainView mainView, LoginModel loginModel) {
        this.mainView = mainView;
        this.loginModel = loginModel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().getClass().equals(JButton.class)) {
            JButton jb = (JButton) e.getSource();

            //System.out.println(jb.getText());
            //System.out.println(mainView.getUsername());
            //System.out.println(mainView.getPassword2());

            if (loginModel.verifyLogin(mainView.getUsername(), mainView.getPassword2())){
                mainView.setLoginDialogVisible(false);
                mainView.setConnectedState(true);
            } else {
                mainView.setConnectedState(false);
            }


            switch (jb.getText()) {
                case "Save":
                break;
            }
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
