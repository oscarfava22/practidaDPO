package Entry.View;


import Entry.Constants.Constants;
import Entry.View.Panels.DatePickerPanel.DatePickerPanel;
import Entry.View.Panels.InitPanel;
import Entry.View.Panels.LandingPanel;
import Entry.View.Panels.ReservePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Date;

public class Entry extends JFrame{

    private final InitPanel initPanel;
    private final ReservePanel reservePanel;
    private final DatePickerPanel dpp;
    private final LandingPanel landingPanel;

    public Entry(ImageIcon imageIcon) {
        setTitle("Entrada");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        initPanel = new InitPanel(imageIcon);
        add(initPanel, Constants.INIT);
        reservePanel = new ReservePanel();
        add(reservePanel,Constants.RESERVE);
        dpp = new DatePickerPanel();
        add(dpp,Constants.DATE_PICKER);
        landingPanel = new LandingPanel();
        add(landingPanel,Constants.LANDING);
    }

    public void switchPanel(String name){
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(),name);
    }

    public void addListeners(MouseListener init, ActionListener reserve,ActionListener picker, ActionListener landing
                                ,ActionListener dateController){
        initPanel.relateControllers(init);
        reservePanel.relateControllers(reserve);
        dpp.relateControllers(picker,dateController);
        landingPanel.relateControllers(landing);
    }

    public void updateTimes(Integer[] year, Integer[] month, Integer[] day, Integer[] hour, Integer[] minute
                                ,boolean keepCurrentSelection){
        dpp.updateTimes(year,month,day,hour,minute,keepCurrentSelection);
    }

    public String getSelectedDate() {
        return dpp.getSelectedTime();
    }
}
