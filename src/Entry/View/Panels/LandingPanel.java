package Entry.View.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel used to show the data of the reservation
 */
public class LandingPanel extends JPanel{

    /**
     * the name of the reservation
     */
    private final JLabel tableID;

    /**
     * the password of the reservation
     */
    private final JTextField tablePassword;

    /**
     * Ok button
     */
    private final JButton ok;

    /**
     * Creates a new landing panel
     */
    public LandingPanel(){
        setLayout(new BorderLayout());
        tableID = new JLabel("FILLER");
        tablePassword = new JTextField("FILLER");

        tablePassword.setEditable(false);

        ok = new JButton("Finish Reservation");


        JLabel tableIDNombe = new JLabel("Table ID");
        JLabel tablePasswordNombre = new JLabel("Table Password");

        JPanel contentWraper1 = new JPanel();
        JPanel nameContent1 = new JPanel();
        nameContent1.setLayout(new BoxLayout(nameContent1,BoxLayout.PAGE_AXIS));
        nameContent1.add(tableIDNombe);
        nameContent1.add(tableID);

        contentWraper1.add(nameContent1);

        JPanel contentWrapper2 = new JPanel();
        JPanel nameContent2 = new JPanel();
        nameContent2.setLayout(new BoxLayout(nameContent2,BoxLayout.PAGE_AXIS));
        nameContent2.add(tablePasswordNombre);
        nameContent2.add(tablePassword);

        contentWrapper2.add(nameContent2);

        JPanel grid = new JPanel(new GridLayout(2,1));
        grid.add(contentWraper1);
        grid.add(contentWrapper2);

        add(grid,BorderLayout.CENTER);
        add(ok,BorderLayout.PAGE_END);
    }

    /**
     * sets the name of the reservation id
     * @param tableID the name of the reservation
     */
    public void setName(String tableID){
        this.tableID.setText(tableID);
    }

    /**
     * sets the password for the reservation
     * @param password the password of the reservation
     */
    public void setPassword(String password){
        this.tablePassword.setText(password);
    }

    /**
     * Relates the controller for the ok listener
     * @param ok the acton listener for the button
     */
    public void relateControllers(ActionListener ok) {
        this.ok.addActionListener(ok);
    }
}
