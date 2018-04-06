package Entry.View.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LandingPanel extends JPanel{


    private final JLabel tableID;
    private final JLabel tablePassword;
    private final JButton ok;

    public LandingPanel(){
        setLayout(new BorderLayout());
        tableID = new JLabel("FILLER");
        tablePassword = new JLabel("FILLER");

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

    public void setData(String tableID, String tablePassword){
        this.tableID.setText(tableID);
        this.tablePassword.setText(tablePassword);
    }

    public void relateControllers(ActionListener ok) {
        this.ok.addActionListener(ok);
    }
}
