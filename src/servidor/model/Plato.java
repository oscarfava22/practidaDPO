package servidor.model;

import java.io.Serializable;

/**
 *
 */
public class Plato implements Serializable {

    private static final long serialVersionUID = 1113799434508676095L;
    private long id;
    private String type;
    private String title;
    private float price;
    private int units;

    /**
     *
     */
    public Plato() {
    }

    /**
     *
     * @param id
     * @param type
     * @param title
     * @param price
     * @param units
     */
    public Plato(long id, String type, String title, float price, int units) {
        this();
        setPlato(id, type, title, price, units);
    }

    /**
     *
     * @param plato
     */
    public Plato(Plato plato) {
        this();
        setPlato(plato.getId(), plato.getType() ,plato.getTitle(), plato.getPrice(), plato.getUnits());
    }

    /**
     *
     * @return
     */
    public synchronized long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public synchronized void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public synchronized String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public synchronized void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public synchronized String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public synchronized void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public synchronized float getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public synchronized void setPrice(float price) {
        this.price = price;
    }

    /**
     *
     * @return
     */
    public synchronized int getUnits() {
        return units;
    }

    /**
     *
     * @param units
     */
    public synchronized void setUnits(int units) {
        this.units = units;
    }

    /**
     *
     * @param id
     * @param type
     * @param title
     * @param price
     * @param units
     */
    public synchronized void setPlato(long id, String type, String title, float price, int units) {
        setId(id);
        setType(type);
        setTitle(title);
        setPrice(price);
        setUnits(units);
    }

    /**
     *
     * @param units
     */
    public synchronized void updateUnits(int units) {
        this.units += units;
    }

    /**
     *
     * @param type
     * @param title
     * @param price
     * @param units
     */
    public synchronized void updatePlato(String type, String title, float price, int units) {
        setType(type);
        setTitle(title);
        setPrice(price);
        setUnits(units);
    }

}
