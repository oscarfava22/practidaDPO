package servidor.view;

import servidor.model.LoginModel;

import javax.swing.*;
import java.awt.*;

public class StatusBarView extends JPanel {

    private JLabel statusLabel;
    private JLabel status;
    private JLabel connectedDevicesLabel;
    private JLabel connectedDevices;

    private JLabel entryServerLabel;
    private JLabel entryServerStatus;
    private JPanel jpEntry;

    private JPanel jpStatus;
    private JPanel jpDevices;

    public StatusBarView() {

        setLayout(new BorderLayout());

        statusLabel = new JLabel("Status: ");
        statusLabel.setForeground(Color.GRAY);
        status = new JLabel("Connected");
        status.setForeground(Color.GREEN);
        jpStatus = new JPanel(new FlowLayout());
        jpStatus.setOpaque(false);
        jpStatus.add(statusLabel);
        jpStatus.add(status);

        entryServerLabel = new JLabel("Entry Client: ");
        entryServerLabel.setForeground(Color.GRAY);
        entryServerStatus = new JLabel("Not Connected");
        entryServerStatus.setForeground(Color.RED);
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

        add(jpStatus, BorderLayout.LINE_START);
        add(jpEntry, BorderLayout.CENTER);
        add(jpDevices, BorderLayout.LINE_END);


        setBackground(Color.DARK_GRAY);
    }

    public void initView(LoginModel loginModel) {

    }

    public void registerControllers() {

    }

    public void setConnectedState (boolean state) {

        if (state) {
            status.setText("Connected");
            status.setForeground(Color.GREEN);
        } else {
            status.setText("Not Connected");
            status.setForeground(Color.RED);
        }
    }

    public void setConnectedDevices(Integer count) {
        connectedDevices.setText(count.toString());
    }

    public void setEntryServerStatus(boolean state) {
        if(state) {
            entryServerStatus.setText("Connected");
            entryServerStatus.setForeground(Color.GREEN);
        } else {

            entryServerStatus.setText("Not Connected");
            entryServerStatus.setForeground(Color.RED);
        }
    }
}
