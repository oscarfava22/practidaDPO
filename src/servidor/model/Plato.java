package servidor.model;

import java.io.Serializable;

/**
 * Clase que representa un Plato e implementa la Interficie Serializable para ser enviada por sockets.
 * Todos los metodos de modificacion son "synchronized" ya que varios servidores dedicados puedes solicitar acceso
 * a la modificacion o creacion de nuevos datos.
 */
public class Plato implements Serializable {

    private static final long serialVersionUID = 1113799434508676095L;
    private long id;
    private String type;
    private String title;
    private float price;
    private int units;

    /**
     * Constructor por defecto.
     */
    public Plato() {
    }

    /**
     * Constructor con todos los atributos de la clase.
     * @param id del plato.
     * @param type del plato.
     * @param title del plato.
     * @param price del plato.
     * @param units del plato.
     */
    public Plato(long id, String type, String title, float price, int units) {
        this();
        setPlato(id, type, title, price, units);
    }

    /**
     * Constructor que recibe otro plato.
     * @param plato que se recibe para copiar.
     */
    public Plato(Plato plato) {
        this();
        setPlato(plato.getId(), plato.getType() ,plato.getTitle(), plato.getPrice(), plato.getUnits());
    }

    /**
     * Permite obtener el id del plato.
     * @return id del plato.
     */
    public synchronized long getId() {
        return id;
    }

    /**
     * Permite asignar el id del plato.
     * @param id para asignar.
     */
    public synchronized void setId(long id) {
        this.id = id;
    }

    /**
     * Permite obtener el tipo de plato.
     * @return el tipo de plato.
     */
    public synchronized String getType() {
        return type;
    }

    /**
     * Permite asignar el tipo de plato.
     * @param type el tipo de plato para asignar.
     */
    public synchronized void setType(String type) {
        this.type = type;
    }

    /**
     * Permite obtener el nombre del plato.
     * @return el nombre del plato.
     */
    public synchronized String getTitle() {
        return title;
    }

    /**
     * Permite aasignar el nombre del plato.
     * @param title el nombre de plato para asignar.
     */
    public synchronized void setTitle(String title) {
        this.title = title;
    }

    /**
     * Permite obtener el precio del plato.
     * @return el precio del plato.
     */
    public synchronized float getPrice() {
        return price;
    }

    /**
     * Permite asignar el precio del plato.
     * @param price el precio para asignar.
     */
    public synchronized void setPrice(float price) {
        this.price = price;
    }

    /**
     * Permite obtener el numero de unidades disponibles del plato.
     * @return el numero de platos disponibles.
     */
    public synchronized int getUnits() {
        return units;
    }

    /**
     * Permite asignar el numero de unidades del plato.
     * @param units el numero de unidades.
     */
    public synchronized void setUnits(int units) {
        this.units = units;
    }

    /**
     * Metodo para asignar los atributos de un plato.
     * @param id del plato.
     * @param type del plato.
     * @param title del plato.
     * @param price del plato.
     * @param units del plato.
     */
    public synchronized void setPlato(long id, String type, String title, float price, int units) {
        setId(id);
        setType(type);
        setTitle(title);
        setPrice(price);
        setUnits(units);
    }

    /**
     * Metodo que permite actualizar el numero de unidades del plato.
     * @param units el numero de platos para sumar o disminuir en funcion del signo del parametro obtenido.
     */
    public synchronized void updateUnits(int units) {
        this.units += units;
    }

    /**
     * Metodo para actualizar todos los atributos de un plato excepto el id.
     * @param type del plato.
     * @param title del plato.
     * @param price del plato.
     * @param units del plato.
     */
    public synchronized void updatePlato(String type, String title, float price, int units) {
        setType(type);
        setTitle(title);
        setPrice(price);
        setUnits(units);
    }

}
