package servidor.model;

public class ServerData {
    public static int bbddConnectionPort;
    public static final String serverIp = "localhost";
    public static String bbddName;
    public static String bbddUsername;
    public static String bbddPassword;
    public static int portRecepcio;
    public static int portReserva;

    private MesasManager mesasManager;






    public void ActualitzaInfoServer(int bbddConnectionPort, String bbddName, String bbddUsername, String bbddPassword, int portRecepcio, int portReserva){
        ServerData.bbddConnectionPort = bbddConnectionPort;
        ServerData.bbddName = bbddName;
        ServerData.bbddUsername = bbddUsername;
        ServerData.bbddPassword = bbddPassword;
        ServerData.portRecepcio = portRecepcio;
        ServerData.portReserva = portReserva;
    }
}
