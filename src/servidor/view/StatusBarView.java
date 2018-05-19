package servidor.view;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class StatusBarView extends JPanel {

    private JLabel statusLabel;
    private JLabel status;
    private JLabel connectedDevicesLabel;
    private JLabel connectedDevices;

    private JLabel entryServerLabel;
    private JLabel entryServerStatus;

    private JPanel jpStatus;
    private JPanel jpEntry;
    private JPanel jpDevices;

    private JPanel jpClients;

    /**
     *
     */
    public StatusBarView() {

        setLayout(new BorderLayout());

        statusLabel = new JLabel("Status: ");
        statusLabel.setForeground(Color.GRAY);
        status = new JLabel();
        setConnectedState(true);
        jpStatus = new JPanel(new FlowLayout());
        jpStatus.setOpaque(false);
        jpStatus.add(statusLabel);
        jpStatus.add(status);

        entryServerLabel = new JLabel("Entry Clients: ");
        entryServerLabel.setForeground(Color.GRAY);
        entryServerStatus = new JLabel("0");
        entryServerStatus.setForeground(Color.CYAN);
        jpEntry = new JPanel(new FlowLayout());
        jpEntry.setOpaque(false);
        jpEntry.add(entryServerLabel);
        jpEntry.add(entryServerStatus);

        connectedDevicesLabel = new JLabel("Connected Devices: ");
        connectedDevicesLabel.setForeground(Color.GRAY);
        connectedDevices = new JLabel("0");
        connectedDevices.setForeground(Color.CYAN);
        jpDevices = new JPanel(new FlowLayout());
        jpDevices.setOpaque(false);
        jpDevices.add(connectedDevicesLabel);
        jpDevices.add(connectedDevices);

        jpClients = new JPanel(new GridLayout(1,2));
        jpClients.setBackground(Color.DARK_GRAY);
        jpClients.add(jpEntry);
        jpClients.add(jpDevices);

        add(jpStatus, BorderLayout.LINE_START);
        //add(jpEntry, BorderLayout.CENTER);
        add(jpClients, BorderLayout.LINE_END);
        setBackground(Color.DARK_GRAY);
    }

    public void setConnectedState(boolean state) {
        if (state) {
            status.setText("Connected");
            status.setForeground(Color.GREEN);
        } else {
            status.setText("Not Connected");
            status.setForeground(Color.RED);
        }
    }

    /**
     *
     * @param count
     */
    public void setConnectedDevices(Integer count) {
        connectedDevices.setText(count.toString());
    }

    /**
     *
     * @param state
     */
    public void setEntryServerStatus(Integer state) {
        /*if(state == 1) {
            entryServerStatus.setText("Connected");
            entryServerStatus.setForeground(Color.GREEN);
        } else if (state == 0){
            entryServerStatus.setText("Not Connected");
            entryServerStatus.setForeground(Color.RED);
        } else {*/
            entryServerStatus.setText(state.toString());
            entryServerStatus.setForeground(Color.CYAN);
        //}
    }
}
