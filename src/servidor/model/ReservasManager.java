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
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ReservasManager {

    public static final int PASSWORD_LENGTH = 8;

    private LinkedList<Reserva> reservas;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat dateTimeFormat;


    public ReservasManager() {
        reservas = new LinkedList<>();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        loadReservas();

    }

    public static boolean isInBbdd(String nombre, String password) throws SQLException{
        boolean esta = false;

        BBDDManager bbdd = BBDDManager.getInstance(Main.BBDD);
        bbdd.connect();
        ResultSet set = bbdd.readQuery("Select c.password, c.nombre from Cliente as c where c.password = '" + password
                + "' and c.nombre = '" + nombre + "';");
        if (set.next()){
            esta = true;
        }

        return esta;
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
                reserva.setPassword(set.getString("r.password"));
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
        System.out.println("2--------------");
        String query = "INSERT INTO Cliente VALUES ('" + reserva.getPassword() + "', '" + request.getName() + "')";
        bbdd.modificationQuery(query);
        query = new StringBuilder().append("INSERT INTO Reserva VALUES(").append(String.valueOf(reserva.getId())).append(",")
                .append(String.valueOf(mesa.getId())).append(",'")
                .append(dateFormat.format(request.getDate())).append("','")
                .append(dateTimeFormat.format(request.getDate())).append("','")
                .append(reserva.getPassword()).append("',").append(reserva.getState())
                .append(");").toString();
        System.out.println("Query::::: " + query);
        bbdd.modificationQuery(query);
        bbdd.disconnect();
    }

    public void updateReservaState(Reserva reserva, int state) {
        for(int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).equals(reserva)) {
                reservas.get(i).setState(state);
            }
        }
        BBDDManager manager = BBDDManager.getInstance(Main.BBDD);
        manager.connect();
        String query = "UPDATE Reserva SET state = "+state+" WHERE id_reserva = "+reserva.getId()+";";
        manager.modificationQuery(query);
        manager.disconnect();
    }

    public LinkedList<Reserva> getReservas() {
        return reservas;
    }

    public ReservaResponse verifyRequest(ReservaRequest reservaRequest) {
        ReservaResponse response = null;
        Mesa mesa;
        if ((mesa=checkAvailability(reservaRequest))!=null) {
            Reserva reserva = new Reserva(SerialGenerator.getReservaId() ,reservaRequest.getName(),
                    reservaRequest.getDate(), reservaRequest.getAmount(), generateRndmPassword(), 0,mesa);
            addReserva(reserva,mesa,reservaRequest);
            System.out.println("New Reserva Created: ");
            System.out.println("\t" + reserva.toString());

            response = new ReservaResponse(reserva.getPassword(),true);
        } else {
            response = new ReservaResponse("", false);
        }

        return response;
    }

    private String generateRndmPassword() {
        char[] availableChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        int numChar = availableChar.length;
        StringBuilder builder = new StringBuilder();
        int pos = 0;
        for (int i = 0; i < PASSWORD_LENGTH; i++){
            pos = new Random().nextInt(numChar);
            builder.append(availableChar[pos]);
        }
        String randomPassword = builder.toString();
        System.out.println("New Password: " + randomPassword);

        boolean available = false;
        try {
            available = isPasswordAvailable(randomPassword);
            if (available){
                return randomPassword;
            }else {
                return generateRndmPassword();
            }
        } catch (SQLException e) {
            return generateRndmPassword();
        }

    }

    public boolean isPasswordAvailable(String randomPassword) throws SQLException {
        BBDDManager bbdd = BBDDManager.getInstance(Main.BBDD);
        bbdd.connect();
        System.out.println("1--------------");
        String query= new StringBuilder().append("SELECT c.password FROM Cliente AS c WHERE c.password LIKE '")
                .append(randomPassword)
                .append("';").toString();
        System.out.println("Query: " + query);
        ResultSet set = bbdd.readQuery(query);
        if (set.next()){
            bbdd.disconnect();
            return false;
        }
        else{
            bbdd.disconnect();
            return true;
        }
    }

    public Mesa checkAvailability(ReservaRequest request) {
        Mesa mesa=null;
        System.out.println("Hola");
        BBDDManager bbdd = BBDDManager.getInstance(Main.BBDD);
        bbdd.connect();
        String query= new StringBuilder().append("SELECT * FROM Mesa as m LEFT JOIN Reserva as r ON m.id_mesa = r.id_mesa WHERE r.id_reserva IS NULL ")
                .append("OR NOT EXISTS(SELECT * FROM Reserva as r1 WHERE r1.sate!=3 and r1.dataConcreta BETWEEN '").append(dateTimeFormat.format(addAnHour(request.getDate(), -1)))
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
