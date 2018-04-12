package Entry.View;


import Entry.Constants.Constants;
import Entry.View.Panels.DatePickerPanel.DatePickerPanel;
import Entry.View.Panels.InitPanel;
import Entry.View.Panels.LandingPanel;
import Entry.View.Panels.ReservePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;

/**
 * Main view, where all useful panes show
 */
public class Entry extends JFrame{

    /**
     * First panel shown
     */
    private final InitPanel initPanel;

    /**
     * Panel where reservations is made
     */
    private final ReservePanel reservePanel;

    /**
     * Panel where dates are picked
     */
    private final DatePickerPanel dpp;

    /**
     * Final panel shown
     */
    private final LandingPanel landingPanel;

    /**
     * Creates new JFrame in which to store the panels
     * @param imageIcon Image to give to the init panel
     */
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

    /**
     * Switches one panel to another of the ones available
     * @param name the name of the panel to choose
     */
    public void switchPanel(String name){
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(),name);
    }

    /**
     * Adds all listeners for the diferent panels
     * @param init listener for init
     * @param reserve listener for reserve
     * @param picker fisrt listener for datePicker
     * @param landing listener for landing
     * @param dateController second listener for datePicker
     * @param wl to kill the client if needed
     */
    public void addListeners(MouseListener init, ActionListener reserve, ActionListener picker, ActionListener landing
            , ItemListener dateController, WindowListener wl){
        initPanel.relateControllers(init);
        reservePanel.relateControllers(reserve);
        dpp.relateControllers(picker,dateController);
        landingPanel.relateControllers(landing);
        this.addWindowListener(wl);
    }

    /**
     * Updates the contents of the date picker
     * @param year the new year data set
     * @param month the new month data set
     * @param day the new day data set
     * @param hour the new hour data set
     * @param minute the new minute data set
     * @param keepCurrentSelection if the current selection has to be changes to the default
     */
    public void updateTimes(Integer[] year, Integer[] month, Integer[] day, Integer[] hour, Integer[] minute
                                ,boolean keepCurrentSelection){
        dpp.updateTimes(year,month,day,hour,minute,keepCurrentSelection);
    }

    /**
     * @return the selected data
     */
    public String getSelectedDate() {
        return dpp.getSelectedTime();
    }

    /**
     * Sets the name of the landing screen
     */
    public void setLandingName() {
        landingPanel.setName(reservePanel.getName());
    }

    /**
     * @return returns if the reserve panel data is empty
     */
    public int reserveNotEmpty() {
        return reservePanel.notEmpty();
    }

    /**
     * Shows a message error
     * @param s the message
     */
    public void showErrorMessage(String s) {
        JOptionPane.showMessageDialog(this,s,"Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Returns the number of people of the reservation
     * @return the number of people
     */
    public int getNumOfPeople() {
        return reservePanel.getNumOfPeople();
    }

    /**
     * Sets the password for the landing panel
     * @param password the password of the reservation
     */
    public void setPassword(String password) {
        landingPanel.setPassword(password);
    }

    /**
     * Clears the fields from the reserve panel
     */
    public void clear() {
        reservePanel.clear();
    }

    /**
     * Returns the name of the reservation
     * @return the name of the reservation
     */
    public String getReserveName() {
        return reservePanel.getName();
    }
}
