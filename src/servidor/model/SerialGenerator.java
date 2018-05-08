package servidor.model;

public class SerialGenerator {

    private static long productId = 0;
    private static long reservaId = 111111;

    public static long getProductId() {
        productId++;
        return productId;
    }

    public static long getReservaId() {
        reservaId++;
        return reservaId;
    }
}
