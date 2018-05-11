package servidor.model;

public class SerialGenerator {

    private static long productId = 0;
    private static long reservaId = 111111;

    public static long getProductId() {
        return productId++;
    }

    public static long getReservaId() {
        return reservaId++;
    }
}
