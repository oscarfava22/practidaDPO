package controller;

import model.LoginModel;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectorViewListener implements ActionListener {

    private MainView mainView;
    private LoginModel loginModel;

    public SelectorViewListener (MainView mainView, LoginModel loginModel) {
        this.mainView = mainView;
        this.loginModel = loginModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (loginModel.getLoginState()) {
            mainView.setSVSelectedButton(e.getActionCommand());
            mainView.setGestionView(e.getActionCommand());
        } else {
            mainView.showLoginDialog();
        }

    }
}
