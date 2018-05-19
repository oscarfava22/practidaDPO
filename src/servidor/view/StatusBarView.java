package servidor.view;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que permite conocer el estado del programa asi como el numero de conexiones tanto con los posibles clientes
 * "Entry" asi como clientes "Reserva".
 * Este panel va colocado en la parte inferior de la vista principal del programa.
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
     * Contructor del panel.
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
        add(jpClients, BorderLayout.LINE_END);
        setBackground(Color.DARK_GRAY);
    }

    /**
     * Permite establecer el estado del programa.
     * @param state estado en funcion del cual se muestra un texto u otro.
     */
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
     * Permite establecer el numero de clientes Reserva conectados.
     * @param count el numero de clientes reserva conectados.
     */
    public void setConnectedDevices(Integer count) {
        connectedDevices.setText(count.toString());
    }

    /**
     * Permite establecer el numero de clientes Entry conectados.
     * @param state
     */
    public void setEntryServerStatus(Integer state) {
        entryServerStatus.setText(state.toString());
        entryServerStatus.setForeground(Color.CYAN);
    }
}
