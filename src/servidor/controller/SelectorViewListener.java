package servidor.controller;

import servidor.model.LoginModel;
import servidor.view.MainView;

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

        mainView.setSVSelectedButton(e.getActionCommand());
        mainView.setGestionView(e.getActionCommand());
    }
}
