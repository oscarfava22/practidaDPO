package Entry.View;


import Entry.View.Panels.DatePickerPanel.DatePickerPanel;
import Entry.View.Panels.InitPanel;
import Entry.View.Panels.ReservePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class Entry extends JFrame{

    public static final String INIT = "Init_Panel";
    public static final String RESERVE = "Reserve_Panel";
    public static final String DATE_PICKER = "DatePicker_Panel";

    private final InitPanel initPanel;
    private final ReservePanel reservePanel;

    public Entry(ImageIcon imageIcon) {
        setTitle("Entrada");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        initPanel = new InitPanel(imageIcon);
        add(initPanel,INIT);
        reservePanel = new ReservePanel();
        add(reservePanel,RESERVE);
        DatePickerPanel dpp = new DatePickerPanel();
        add(dpp,DATE_PICKER);
    }

    public void switchPanel(String name){
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(),name);
    }

    public void addListeners(MouseListener init, ActionListener reserve){
        initPanel.relateControllers(init);
        reservePanel.relateControllers(reserve);
    }
}
