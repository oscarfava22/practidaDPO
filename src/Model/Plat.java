package Model;

import java.io.Serializable;

public class Plat implements Serializable {

    private long id;
    private String nom;
    private float price;
    private int unitats;
    private int tipus;
    private int unitatsSeleccionades; //Indica el nombre d'unitats selecionades per l'usuari a la carta.
    private boolean servit; //Indica si s'ha servit o no un plat, de cara a mostrar l'estat de la comanda.
    private int indexPlat;

    /**
     * Constructor del objecto sin seleccionar unidades
     * @param nom nombre del plato
     * @param price precio del plato
     * @param unitats unidades de ese plato disponibles
     * @param tipus tipos que indentifica si es postre, principal, entrante y bebida
     */
    public Plat (long id, String nom, float price, int unitats, int tipus) {

        this.id = id;
        this.nom = nom;
        this.price = price;
        this.unitats = unitats;
        this.tipus = tipus;
    }

    /**
     * Constructor con todos los parametros
     * @param nom nombre del plato
     * @param price precio del plato
     * @param unitats unidades de ese plato disponibles
     * @param tipus tipos que indentifica si es postre, principal, entrante y bebida
     * @param unitatsSeleccionades unidades que se piden en el momento actual
     */
    public Plat (long id, String nom, float price, int unitats, int tipus, int unitatsSeleccionades, boolean servit, int indexPlat) {

        this.id = id;
        this.nom = nom;
        this.price = price;
        this.unitats = unitats;
        this.tipus = tipus;
        this.unitatsSeleccionades = unitatsSeleccionades;
        this.servit = servit;
        this.indexPlat = indexPlat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Devuelve el tipo de plato
     * @return tipo de plato
     */
    public int getTipus() {
        return tipus;
    }

    /**
     * Devuelve el nombre del plato
     * @return el nombre del plato
     */
    public String getNom() {
        return nom;
    }

    /**
     * Devuelve el precio del plato
     * @return precio del plato
     */
    public float getPrice() {
        return price;
    }

    /**
     * Devuelve las unidades restantes del plato
     * @return unidades restantes de el plato
     */
    public int getUnitats() {
        return unitats;
    }

    public int getIndexPlat() {
        return indexPlat;
    }

    public void setIndexPlat(int indexPlat) {
        this.indexPlat = indexPlat;
    }

    /**
     * Devuelve si el plato esta servido o no
     * @return si se ha servido el plato
     */
    public boolean isServit() {
        return servit;
    }

    /**
     * Para poner las unidades actuales
     * @param unitats unidades disponibles
     */
    public void setUnitats(int unitats) { this.unitats = unitats; }

    /**
     * Augmenta en una unidad las unidades a realizar
     */
    public void incrementaUnitatsSeleccionades () {
        unitatsSeleccionades++;
    }

    /**
     * Decrementa en una unidadad las unidades a realizar
     */
    public void decrementaUnitatsSeleccionades () {
        unitatsSeleccionades--;
    }

    /**
     * Devuelve las unidades a servir
     * @return unidades a servir
     */
    public int getUnitatsSeleccionades() {
        return unitatsSeleccionades;
    }

    /**
     * Establece las unidades seleccionadas por el cliente
     * @param unitatsSeleccionades unidades del plato que se han de servir
     */
    public void setUnitatsSeleccionades (int unitatsSeleccionades) {
        this.unitatsSeleccionades = unitatsSeleccionades;
    }

    public void setServit () {
        servit = true;
    }
}
