package servidor.controller;

import servidor.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBarViewListener implements ActionListener {

    private MainView mainView;

    public MenuBarViewListener(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "File":
                System.out.println("File");
                break;
            case "Settings":
                System.out.println("Settings");
                mainView.showSettingsDialog();
                break;
            case "Log In":
                System.out.println("Log In");

                break;
            case "Exit":
                System.out.println("Exit");
                System.exit(0);
                break;
        }
    }
}
