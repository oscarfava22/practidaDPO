package servidor.controller;

import servidor.Main;
import servidor.model.Database.BBDDManager;
import servidor.model.Database.InfoResultSetReserva;
import servidor.model.MesasManager;
import servidor.view.MainView;
import servidor.view.MesaView;
import servidor.view.MesasView;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Timer;

public class MesasViewListener implements MouseInputListener{

    /**
     * Atributos de la clase
     */
    private MainView mainView;
    private MesasManager mesasManager;

    /**
     * Constructor de la clase
     * @param mainView
     * @param mesasManager
     */
    public MesasViewListener(MainView mainView, MesasManager mesasManager) {
        this.mainView = mainView;
        this.mesasManager = mesasManager;
    }

    /**
     * Función que se ejecuta cuando el usuario clica en una de las mesas de la lista de la parte izquierda de la
     * pantalla GestionMesasView
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        //Obtenemos la mesa
        MesaView mv = (MesaView) e.getSource();
        mv.setSelected(true);
        mv.setLabelsBackground(true);

        //Get id
        String string = mv.getJlIdMesa().getText().toString();
        String idMesaSeleccionada = string.substring(string.indexOf(":") + 2);

        //Actualizamos la pantalla con la mesa apretada
        mesasManager.setIdMesaSeleccionada(idMesaSeleccionada);
        mainView.getGestionMesasView().getMesasView().setLabelsBackground(idMesaSeleccionada, true);
        mainView.getGestionMesasView().setIdMesaSeleccionada(idMesaSeleccionada);

        //Cambiamos el color de las mesas que no estan seleccionadas
        MesasView mesasView = mainView.getGestionMesasView().getMesasView();
        for (int i = 0; i < mesasView.getMesasView().size(); i++){
            if (!mv.equals(mesasView.getMesasView().get(i))){
                mesasView.getMesasView().get(i).setLabelsBackground(false);
                mesasView.getMesasView().get(i).setSelected(false);
            }else {
                mesasView.getMesasView().get(i).setLabelsBackground(true);
                mesasView.getMesasView().get(i).setSelected(true);
            }
        }

        for (int i = 0; i < mesasView.getMesasView().size(); i++){
            if (mesasView.getMesasView().get(i).getJlIdMesa().getId() == idMesaSeleccionada){
                mesasView.getMesasView().get(i).setLabelsBackground(true);
            }
        }

        //Llamar al getInstance
        BBDDManager bbddManager = BBDDManager.getInstance(Main.BBDD);

        // Del objeto getInstance hacer un connect
        bbddManager.connect();

        //Querie --> select reserves de la taula amb id especificat;
        String query = "SELECT r.id_reserva, r.id_mesa, r.password, r.data, r.dataConcreta FROM Reserva AS r Where r.id_mesa = " + idMesaSeleccionada + " ;";

        //Obtenir resultSet de la query
        ResultSet resultSet = bbddManager.readQuery(query);

        //Llegir informació del resultSet
        try {
            ArrayList<InfoResultSetReserva> reservas = llegirResultSetSelectReservas(resultSet);

            for (int i = 0; i < reservas.size(); i++){
                //Copiem la reserva
                InfoResultSetReserva reserva = reservas.get(i);

                //Obtenim el password i fem la query
                String password = reserva.getPassword();
                String queryNombre = "SELECT c.nombre FROM Cliente AS c WHERE c.password = '" + password + "';";

                //Obtenim el resultSet i obtenim el nom
                ResultSet resultSetNombre = bbddManager.readQuery(queryNombre);
                resultSetNombre.next();
                String nombre = resultSetNombre.getString("nombre");
                //Afegim el nom a la reserva
                reserva.setNombre(nombre);

                //Eliminem la reserva antiga i afegim la nova que si té el nom
                reservas.remove(i);
                reservas.add(reserva);
            }

            //Ordenar las reservas según la fecha de reserva
            reservas.sort(new Comparator<InfoResultSetReserva>() {
                @Override
                public int compare(InfoResultSetReserva o1, InfoResultSetReserva o2) {
                    Date date1 = o1.getDate();
                    Date date2 = o2.getDate();

                    if (date1.before(date2) || date1.after(date2)){
                        return date1.compareTo(date2);
                    }else{
                        Time time1 = o1.getTime();
                        Time time2 = o2.getTime();
                        return time1.compareTo(time2);
                    }
                }
            });

            //Refrescamos el panel derecho, el lis
            mainView.refreshReservas(reservas);

        } catch (SQLException e1) {
        }

        //Actualizar mainview
        mainView.refreshMesas(this);

        // Del objeto getInstance, desconectar
        bbddManager.disconnect();
    }

    /**
     * Esta función se ejecutará para extraer la información de las reservas
     * @param resultSet ResultSet
     * @return ArrayList of InfoResultSetReserva
     * @throws SQLException
     */
    public ArrayList<InfoResultSetReserva> llegirResultSetSelectReservas(ResultSet resultSet) throws SQLException {
        ArrayList<InfoResultSetReserva> reservas = new ArrayList<InfoResultSetReserva>();

        while (resultSet.next()){
            int idReserva = resultSet.getInt("id_reserva");
            int idMesa = resultSet.getInt("id_mesa");
            String password = resultSet.getString("password");
            Date date = resultSet.getDate("data");
            Time time = resultSet.getTime("dataConcreta");

            InfoResultSetReserva reserva = new InfoResultSetReserva(idReserva, idMesa, password, date, time);
            System.out.println(reserva.toString());

            reservas.add(reserva);
        }

        return reservas;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
