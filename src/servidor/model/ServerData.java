package servidor.model;

import com.google.gson.JsonObject;

public class ServerData {
    public static int connectionPort;
    public static String serverIp;
    public static String bbddName;
    public static String bbddUsername;
    public static String bbddPassword;
    public static int portRecepcio;
    public static int portReserva;

    //Atributos
    private MesasManager mesasManager;

    //Constructor
    public ServerData(JsonObject jsonObjectFromConfigFile, MesasManager mesasManager){
        this.mesasManager = mesasManager;
        ServerData.portRecepcio = jsonObjectFromConfigFile.get("port_recepcio").getAsInt();
        ServerData.portReserva = jsonObjectFromConfigFile.get("port_reserva").getAsInt();

        JsonObject bbddInfo = jsonObjectFromConfigFile.get("bbdd_info").getAsJsonObject();
        ServerData.serverIp = bbddInfo.get("ip").getAsString();
        ServerData.bbddName = bbddInfo.get("name").getAsString();
        ServerData.connectionPort = bbddInfo.get("connection_port").getAsInt();
        ServerData.bbddUsername = bbddInfo.get("username").getAsString();
        ServerData.bbddPassword = bbddInfo.get("password").getAsString();
    }





    public void ActualitzaInfoServer(int bbddConnectionPort, String bbddName, String bbddUsername, String bbddPassword, int portRecepcio, int portReserva){
        ServerData.connectionPort = bbddConnectionPort;
        ServerData.bbddName = bbddName;
        ServerData.bbddUsername = bbddUsername;
        ServerData.bbddPassword = bbddPassword;
        ServerData.portRecepcio = portRecepcio;
        ServerData.portReserva = portReserva;
    }
}
