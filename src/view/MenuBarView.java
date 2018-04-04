package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuBarView extends JMenuBar {

    private JMenu jmFile;
    private JMenuItem jmiSettings;
    private JSeparator jSeparator;
    private JMenuItem jmiExit;

    private JMenu jmDatabase;
    private JMenuItem jmiLogin;

    public MenuBarView () {

        jmFile = new JMenu("File");
        jmiSettings = new JMenuItem("Settings");
        jSeparator = new JSeparator();
        jmiExit = new JMenuItem("Exit");

        jmFile.add(jmiSettings);
        jmFile.add(jSeparator);
        jmFile.add(jmiExit);
        add(jmFile);

        jmDatabase = new JMenu("Database");
        jmiLogin = new JMenuItem("Log in");

        jmDatabase.add(jmiLogin);
        add(jmDatabase);
    }

    public void registerControllers(ActionListener menuBarViewListener) {

        jmiSettings.addActionListener(menuBarViewListener);
        jmiSettings.setActionCommand("Settings");
        jmiExit.addActionListener(menuBarViewListener);
        jmiExit.setActionCommand("Exit");
        jmiLogin.addActionListener(menuBarViewListener);
        jmiLogin.setActionCommand("Log In");
    }
}
