package servidor.model.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SerialMesasGenerator {
    public static int serial = 0;

    public static int getSerial(){
        System.out.println("Hola");
        System.out.println("Hola");
        try {
            serial = getMaxIdFromDatabase();
        } catch (SQLException e) {
            serial = 0;
        }
        serial++;
        return serial;
    }

    private static int getMaxIdFromDatabase() throws SQLException {
        BBDDManager bbddManager = BBDDManager.getInstance("Restaurant");
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
