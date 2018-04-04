package Entry.View;


import Entry.View.Panels.InitPanel;

import javax.swing.*;

public class Entry extends JFrame{


    public Entry(ImageIcon imageIcon) {
        setTitle("Entrada");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        InitPanel initPanel = new InitPanel(imageIcon);
        add(initPanel);
    }
}
