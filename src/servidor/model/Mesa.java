package servidor.model;

public class Mesa {

    /**
     * Atributos de la clase
     */
    private int id;
    private int numComensales;

    /**
     * Constructor sin parámetros
     */
    public Mesa() {}


    /**
     * Constructor con parámetros
     * @param id: id de la mesa
     * @param numComensales: número de comensales de la mesa
     */
    public Mesa(int id, int numComensales) {
        this.numComensales = numComensales;
        this.id = id;
    }

    /**
     * Getters & Setters
     * @return
     */
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

    /**
     * Función ToString
     * @return
     */
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
