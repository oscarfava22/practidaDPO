package servidor.model;

public class SerialGenerator {

    private static Integer id = 0;

    public static Integer getId() {
        id++;
        return id;
    }
}
