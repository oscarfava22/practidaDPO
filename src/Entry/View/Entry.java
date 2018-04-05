package Entry.View;


import Entry.View.Panels.InitPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class Entry extends JFrame{

    public static final String INIT = "Init_Panel";
    public static final String RESERVAR = "Test";

    private final InitPanel initPanel;

    public Entry(ImageIcon imageIcon) {
        setTitle("Entrada");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        initPanel = new InitPanel(imageIcon);
        add(initPanel,INIT);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Cambio"));
        add(panel,"Test");
    }

    public void switchPanel(String name){
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(),name);
    }

    public void addListeners(MouseListener init){
        initPanel.relateControllers(init);
    }
}
