package servidor.model.Database;

public class SerialMesasGenerator {
    public static int serial = 0;

    public static int getSerial(){
        serial++;
        return serial;
    }
}
