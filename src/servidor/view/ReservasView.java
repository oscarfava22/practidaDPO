package servidor.view;

import servidor.model.Database.InfoResultSetReserva;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReservasView extends JPanel {

    public static final String NOMBRE_RESERVA_TAG = "Nombre de la Reserva";
    public static final String HORA_TAG = "Hora (hh:mm)";
    public static final String FECHA_TAG = "Fecha (dd/mm/aaaa)";

    private ArrayList<ReservaView> reservasView;
    private JPanel jpMain;
    private JPanel jpTitle;
    private JPanel jpReservas;
    private JScrollPane jspReservas;

    public ReservasView() {
        setLayout(new BorderLayout());

        reservasView = new ArrayList<>();

        jpReservas = new JPanel();
        jpReservas.setLayout(new BoxLayout(jpReservas, BoxLayout.Y_AXIS));

        jspReservas = new JScrollPane(jpReservas);
        jspReservas.getViewport().setView(jpReservas);
        jspReservas.setBorder(BorderFactory.createEmptyBorder());

        jpTitle = new JPanel();
        jpTitle.setLayout(new GridLayout(1, 3));
        jpTitle.add(new JLabel(NOMBRE_RESERVA_TAG));
        jpTitle.add(new JLabel(FECHA_TAG));
        jpTitle.add(new JLabel(HORA_TAG));

        jpMain = new JPanel(new BorderLayout());
        jpMain.add(jpTitle, BorderLayout.PAGE_START);

        jpMain.add(jspReservas, BorderLayout.CENTER);

        add(jpMain, BorderLayout.CENTER);
    }

    public void initReservas(ArrayList<InfoResultSetReserva> reservas) {
        for(InfoResultSetReserva reserva : reservas) {
            ReservaView reservaView = new ReservaView(reserva);
            reservasView.add(reservaView);
            jpReservas.add(reservaView);
        }
        jspReservas = new JScrollPane(jpReservas);
        jpMain.add(jspReservas, BorderLayout.CENTER);
    }

    public void addReserva(InfoResultSetReserva reserva) {
        ReservaView reservaView = new ReservaView(reserva);
        reservasView.add(reservaView);
        jpReservas.add(reservaView);
        jpReservas.updateUI();
    }

    public void refreshReservas(ArrayList<InfoResultSetReserva> reservas) {
        reservasView.clear();
        jpMain.removeAll();

        jpMain.add(jpTitle, BorderLayout.PAGE_START);
        jpMain.add(jpReservas, BorderLayout.CENTER);

        initReservas(reservas);
        this.updateUI();
        paintAll(getGraphics());
    }
}
