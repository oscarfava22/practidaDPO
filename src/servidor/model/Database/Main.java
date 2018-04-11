package servidor.model.Database;

public class Main {
    public static void main(String[] args) {
        BBDDManager.setUsername("test");
        BBDDManager manager = BBDDManager.getInstance("Restaurant");
        manager.connect();
        manager.modificationQuery("Insert into Mesa Values (28,2134556)");
        manager.disconnect();
    }
}
