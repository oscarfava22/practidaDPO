package servidor.model;

import Network.Reserva.ReservaRequest;
import Network.Reserva.ReservaResponse;
import servidor.Main;
import servidor.model.Database.BBDDManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class ReservasManager {

    private LinkedList<Reserva> reservas;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat dateTimeFormat;


    public ReservasManager() {
        reservas = new LinkedList<>();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        loadReservas();

    }

    public void loadReservas() {
        /*try {
        Reserva[] reservas = (Reserva[]) JsonIO.readJson(Reserva[].class, "/data/json/reservas.json");
        this.reservas.addAll(Arrays.asList(reservas));

        System.out.println("Num Reservas: " + this.reservas.size());

        for (Reserva reserva : reservas) {
            reserva.setId(SerialGenerator.getReservaId());
            System.out.println(reserva.toString());
        }

        } catch (IOException e) {
          e.printStackTrace();
        }*/
        BBDDManager bbdd = BBDDManager.getInstance(Main.BBDD);
        bbdd.connect();
        ResultSet set = bbdd.readQuery("SELECT * FROM Reserva as r NATURAL JOIN Mesa as m NATURAL JOIN Cliente as c;");
        try {
            while(set.next()){
                Reserva reserva = new Reserva();
                reserva.setAmount(set.getInt("m.num_comensales"));
                reserva.setDate(set.getDate("r.data"));
                reserva.setId(set.getInt("r.id_reserva"));
                reserva.setName(set.getString("c.nombre"));
                reserva.setPassword(set.getString("c.password"));
                reserva.setState(set.getInt("r.state"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        bbdd.disconnect();

    }

    public void addReservas(LinkedList<Reserva> reservas) {
        //TODO Aca tambien add Reservas to BBDD
        this.reservas.addAll(reservas);
    }

    public void addReserva(Reserva reserva,Mesa mesa,ReservaRequest request) {
        reservas.add(reserva);
        BBDDManager bbdd = BBDDManager.getInstance(Main.BBDD);
        bbdd.connect();
        String query = new StringBuilder().append("INSERT INTO Reserva VALUES(").append(String.valueOf(reserva.getId())).append(",")
                .append(String.valueOf(mesa.getId())).append(",'")
                .append(dateFormat.format(request.getDate())).append("','")
                .append(dateTimeFormat.format(request.getDate())).append("','")
                .append(reserva.getPassword()).append("',").append(reserva.getState())
                .append(");").toString();
        bbdd.modificationQuery(query);
        bbdd.disconnect();
    }

    public void removeReserva() {

    }

    public LinkedList<Reserva> getReservas() {
        return reservas;
    }

    public ReservaResponse verifyRequest(ReservaRequest reservaRequest) {
        ReservaResponse response = null;
        Mesa mesa;
        if ((mesa=checkAvailability(reservaRequest))!=null) {
            Reserva reserva = new Reserva(SerialGenerator.getReservaId() ,reservaRequest.getName(), reservaRequest.getDate(), reservaRequest.getAmount(), "Pass", 0);
            addReserva(reserva,mesa,reservaRequest);
            System.out.println("New Reserva Created: ");
            System.out.println("\t" + reserva.toString());

            response = new ReservaResponse(reserva.getPassword(),true);
        } else {
            response = new ReservaResponse("", false);
        }

        return response;
    }

    public Mesa checkAvailability(ReservaRequest request) {
        Mesa mesa=null;
        BBDDManager bbdd = BBDDManager.getInstance(Main.BBDD);
        bbdd.connect();
        String query= new StringBuilder().append("SELECT * FROM Mesa as m LEFT JOIN Reserva as r ON m.id_mesa = r.id_mesa WHERE r.id_reserva IS NULL ")
                .append("and NOT EXISTS(SELECT * FROM Reserva as r1 WHERE r1.dataConcreta BETWEEN '").append(dateTimeFormat.format(addAnHour(request.getDate(), -1)))
                .append("' AND '")
                .append(dateTimeFormat.format(addAnHour(request.getDate(), 1)))
                .append("' and r1.id_mesa = m.id_mesa);").toString();
        ResultSet set = bbdd.readQuery(query);
        try {
            if(set.next()){
                mesa = new Mesa();
                mesa.setId(set.getInt("m.id_mesa"));
                mesa.setNumComensales(set.getInt("m.num_comensales"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mesa;
    }

    private Date addAnHour(Date date, int direction){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY,direction);
        return cal.getTime();
    }

    public Reserva searchReserva(String name, String password) {


        for(Reserva reserva : reservas) {
            if(reserva.getName().equals(name) && reserva.getPassword().equals(password)) {
                if(reserva.getState() == 0) {
                    reserva.setState(1);
                    BBDDManager manager = BBDDManager.getInstance(Main.BBDD);
                    manager.connect();
                    String query = new StringBuilder().append("UPDATE Reserva SET state = ").append(1)
                            .append(" WHERE password = '").append(password).append("';").toString();
                    manager.modificationQuery(query);
                    manager.disconnect();
                    return reserva;
                }
                break;
            }
        }

        return null;
    }
}
