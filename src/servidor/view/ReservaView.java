package servidor.view;

import servidor.model.Database.InfoResultSetReserva;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ReservaView extends JPanel {
    private Border compounBorder =  BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY),
            BorderFactory.createEmptyBorder(10,10,10,10));

    private CustomLabel jlNombre;
    private CustomLabel jlFecha;
    private CustomLabel jlHora;

    public ReservaView() {
        setLayout(new GridLayout(1, 3));
    }

    public ReservaView(InfoResultSetReserva reserva){
        this();
        initReservas(reserva);
    }

    public void initReservas(InfoResultSetReserva reserva) {
        initLabels(reserva);
        add(jlNombre);
        add(jlFecha);
        add(jlHora);
    }

    public void initLabels(InfoResultSetReserva reserva) {
        jlNombre = new CustomLabel(String.valueOf(reserva.getNombre()));
        jlNombre.setBorder(compounBorder);
        jlNombre.setOpaque(true);
        setJlNombre(String.valueOf(reserva.getNombre()));

        jlFecha = new CustomLabel(String.valueOf(reserva.getDate()));
        jlFecha.setBorder(compounBorder);
        jlFecha.setOpaque(true);
        setJlFecha(String.valueOf(reserva.getDate()));

        jlHora = new CustomLabel(String.valueOf(reserva.getTime()));
        jlHora.setBorder(compounBorder);
        jlHora.setOpaque(true);
        setJlHora(String.valueOf(reserva.getTime()));
    }

    public void updateReservaView(InfoResultSetReserva reserva) {
        setJlNombre(reserva.getNombre());
        setJlFecha(String.valueOf(reserva.getDate()));
        setJlHora(String.valueOf(reserva.getTime()));
    }


    public void setJlNombre(String s) {
        jlNombre.setText(s);
    }

    public void setJlFecha(String s) {
        jlFecha.setText(s);
    }

    public  void setJlHora(String s){
        jlHora.setText(s);
    }


}
