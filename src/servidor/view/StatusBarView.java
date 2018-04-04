package servidor.view;

import servidor.model.LoginModel;

import javax.swing.*;
import java.awt.*;

public class StatusBarView extends JPanel {

    private JLabel statusLabel;
    private JLabel status;
    private JLabel connectedDevicesLabel;
    private JLabel connectedDevices;
    private JPanel jpStatus;
    private JPanel jpDevices;

    public StatusBarView() {

        setLayout(new BorderLayout());
        statusLabel = new JLabel("Status: ");
        statusLabel.setForeground(Color.GRAY);
        status = new JLabel("Not Connected");
        status.setForeground(Color.RED);
        jpStatus = new JPanel(new FlowLayout());
        jpStatus.setOpaque(false);
        jpStatus.add(statusLabel);
        jpStatus.add(status);

        connectedDevicesLabel = new JLabel("Connected Devices: ");
        connectedDevicesLabel.setForeground(Color.GRAY);
        connectedDevices = new JLabel("0");
        connectedDevices.setForeground(Color.CYAN);
        jpDevices = new JPanel(new FlowLayout());
        jpDevices.setOpaque(false);
        jpDevices.add(connectedDevicesLabel);
        jpDevices.add(connectedDevices);

        add(jpStatus, BorderLayout.LINE_START);
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
        //updateUI();
    }
}
