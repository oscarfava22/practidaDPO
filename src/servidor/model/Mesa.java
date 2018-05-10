package servidor.model;

public class Mesa {

    private int id;
    private int numComensales;

    public Mesa() {}

    //Constructor
    public Mesa(int id, int numComensales) {
        this.numComensales = numComensales;
        this.id = id;
    }

    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumComensales() {
        return numComensales;
    }

    public void setNumComensales(int numComensales) {
        this.numComensales = numComensales;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Id Mesa: ");
        stringBuilder.append(id);

        stringBuilder.append(System.lineSeparator());

        stringBuilder.append("Num Comensales: ");
        stringBuilder.append(numComensales);

        stringBuilder.append(System.lineSeparator());

        return stringBuilder.toString();
    }
}
