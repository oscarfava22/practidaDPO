package servidor.model;

import servidor.Main;
import servidor.model.Database.BBDDManager;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Generador automatico de "id" para productos (platos) y reservas.
 */
public class SerialGenerator {

    /**
     * Constantes que se actualizarán cuando la base de datos sea modificada
     */
    private static long productId = getMaxIdProduct();
    private static long reservaId = getMaxIdReserva();

    /**
     * Función para obtener el Id máximo de las reservas que se encuentran en la base de datos
     * @return el id máximo
     */
    public static long getMaxIdReserva() {
        try {
            reservaId = getIdReserva();
        } catch (SQLException e) {
            reservaId = 0;
        }
        reservaId++;
        return reservaId;


    }

    /**
     * Permite obtener un id para una reserva mediante la base de datos.
     * @return un id para una reserva nueva.
     * @throws SQLException
     */
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

    /**
     * Permite obtener el ultimo id asignado para evitar repeticiones.
     * @return el ultimo id asignado.
     */
    public static long getMaxIdProduct() {
        try {
            productId = getIdProduct();
        } catch (SQLException e) {
            productId = 0;
        }
        productId++;
        return productId;


    }

    /**
     * Permite obtener un id para un producto mediante la base de datos..
     * @return un id para un producto nuevo.
     * @throws SQLException
     */
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

    /**
     * Permite obtener un id para un producto nuevo.
     * @return el id para un producto nuevo.
     */
    public static long getProductId() {
        return productId++;
    }

    /**
     * Permite obtener un id para una reserva nueva.
     * @return el id para una reserva nueva.
     */
    public static long getReservaId() {
        return reservaId++;
    }
}
