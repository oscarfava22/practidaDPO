package servidor.model;

public class Mesa {

    private String id;
    private int numComensales;

    //Constructor
    public Mesa(int numComensales, String id) {
        this.numComensales = numComensales;
        this.id = id;
    }

    //Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumComensales() {
        return numComensales;
    }

    public void setNumComensales(int numComensales) {
        this.numComensales = numComensales;
    }
}
