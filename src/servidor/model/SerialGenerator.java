package servidor.model;

import servidor.Main;
import servidor.model.Database.BBDDManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SerialGenerator {

    private static long productId = getMaxIdProduct();
    private static long reservaId = getMaxIdReserva();

    public static long getMaxIdReserva() {
        try {
            reservaId = getIdReserva();
        } catch (SQLException e) {
            reservaId = 0;
        }
        reservaId++;
        return reservaId;


    }

    public static long getIdReserva() throws SQLException {
        BBDDManager bbddManager = BBDDManager.getInstance(Main.BBDD);
        // Del objeto getInstance hacer un connect
        bbddManager.connect();


        //Querie --> eliminar la taula amb id de taula idMesaSeleccionada
        String query = "SELECT r.id_reserva AS maxId FROM Reserva AS r ORDER BY maxId DESC LIMIT 1;";

        ResultSet rs = bbddManager.readQuery(query);

        rs.next();
        int maxId = rs.getInt("maxId");

        // Del objeto getInstance, desconectar
        bbddManager.disconnect();

        return maxId;
    }

    public static long getMaxIdProduct() {
        try {
            productId = getIdProduct();
        } catch (SQLException e) {
            productId = 0;
        }
        productId++;
        return productId;


    }

    public static long getIdProduct() throws SQLException {
        BBDDManager bbddManager = BBDDManager.getInstance(Main.BBDD);
        // Del objeto getInstance hacer un connect
        bbddManager.connect();


        //Querie --> eliminar la taula amb id de taula idMesaSeleccionada
        String query = "SELECT p.id_plato AS maxId FROM Plato AS p ORDER BY maxId DESC LIMIT 1;";

        ResultSet rs = bbddManager.readQuery(query);

        rs.next();
        int maxId = rs.getInt("maxId");

        // Del objeto getInstance, desconectar
        bbddManager.disconnect();

        return maxId;
    }

    public static long getProductId() {
        return productId++;
    }

    public static long getReservaId() {
        return reservaId++;
    }
}
