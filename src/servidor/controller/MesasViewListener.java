package servidor.controller;

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

    private MainView mainView;
    private MesasManager mesasManager;

    public MesasViewListener(MainView mainView, MesasManager mesasManager) {
        this.mainView = mainView;
        this.mesasManager = mesasManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        MesaView mv = (MesaView) e.getSource();
        mv.setSelected(true);
        mv.setLabelsBackground(true);

        System.out.println("ID mesa apretada: " + mv.getJlIdMesa().getText().toString());
        mainView.getGestionMesasView().setIdMesaSeleccionada(mv.getJlIdMesa().getText().toString());

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

        //Llamar al getInstance
        BBDDManager bbddManager = BBDDManager.getInstance("Restaurant");

        // Del objeto getInstance hacer un connect
        bbddManager.connect();

        //Querie --> select reserves de la taula amb id especificat
        String idMesaToString = mv.getJlIdMesa().getText().toString();
        String query = "SELECT r.id_reserva, r.id_mesa, r.password, r.data, r.dataConcreta FROM Reserva AS r Where r.id_mesa = " + idMesaToString + " ;";

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

/*          ----PRUEBA SIN BBDD----
            ArrayList<InfoResultSetReserva> reservas = new ArrayList<InfoResultSetReserva>();
            InfoResultSetReserva r1 = new InfoResultSetReserva(12, 56, "hola", new Date(), new Time(15, 34, 20));
            r1.setNombre("Xose");
            reservas.add(r1);
            InfoResultSetReserva r2 = new InfoResultSetReserva(13, 56, "hola2", new Date(), new Time(18, 54, 40));
            r2.setNombre("Bernard");
            reservas.add(r2);
            InfoResultSetReserva r3 = new InfoResultSetReserva(14, 56, "hola3", new Date(), new Time(05, 24, 15));
            r3.setNombre("Olksiy");
            reservas.add(r3);
            InfoResultSetReserva r4 = new InfoResultSetReserva(15, 56, "hola4", new Date(), new Time(11, 54, 06));
            r4.setNombre("Oscar");
            reservas.add(r4);
*/
            reservas.sort(new Comparator<InfoResultSetReserva>() {
                //TODO: Revisar si funciona bien el Comparator
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

            mainView.refreshReservas(reservas);

        } catch (SQLException e1) {
            //System.out.println("Error al llegir RESULTSET");
        }

        //Actualizar mainview
        mainView.refreshMesas(this);

        // Del objeto getInstance, desconectar
//        bbddManager.disconnect();


    }

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
