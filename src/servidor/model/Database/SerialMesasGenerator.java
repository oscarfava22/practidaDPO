package servidor.model.Database;

import servidor.Main;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase para generar los ids de las mesas del restaurante
 */
public class SerialMesasGenerator {
    public static int serial = 0;

    /**
     * Obtener el id serial para la próxima mesa
     * @return
     */
    public static int getSerial(){
        try {
            serial = getMaxIdFromDatabase();
        } catch (SQLException e) {
            serial = 0;
        }
        serial++;
        return serial;
    }

    /**
     * Obtener el id más grande que haya guardado en la tabla Mesa de la base de datos
     * @return
     * @throws SQLException
     */
    private static int getMaxIdFromDatabase() throws SQLException {
        BBDDManager bbddManager = BBDDManager.getInstance(Main.BBDD);
        // Del objeto getInstance hacer un connect
        bbddManager.connect();


        //Querie --> eliminar la taula amb id de taula idMesaSeleccionada
        String query = "SELECT m.id_mesa AS maxId FROM Mesa AS m ORDER BY m.id_mesa DESC LIMIT 1;";

        ResultSet rs = bbddManager.readQuery(query);

        rs.next();
        int maxId = rs.getInt("maxId");

        // Del objeto getInstance, desconectar
        bbddManager.disconnect();

        return maxId;
    }
}
