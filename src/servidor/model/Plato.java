package servidor.model;

import java.io.Serializable;

public class Plato implements Serializable {

    private long id;
    private String type;
    private String title;
    private float price;
    private int units;

    public Plato() {

    }

    public Plato(long id, String type, String title, float price, int units) {
        this();
        setPlato(id, type, title, price, units);
    }

    public Plato(Plato plato) {
        this();
        setPlato(plato.getId(), plato.getType() ,plato.getTitle(), plato.getPrice(), plato.getUnits());
    }

    public synchronized long getId() {
        return id;
    }

    public synchronized void setId(long id) {
        this.id = id;
    }

    public synchronized String getType() {
        return type;
    }

    public synchronized void setType(String type) {
        this.type = type;
    }

    public synchronized String getTitle() {
        return title;
    }

    public synchronized void setTitle(String title) {
        this.title = title;
    }

    public synchronized float getPrice() {
        return price;
    }

    public synchronized void setPrice(float price) {
        this.price = price;
    }

    public synchronized int getUnits() {
        return units;
    }

    public synchronized void setUnits(int units) {
        this.units = units;
    }

    public synchronized void setPlato(long id, String type, String title, float price, int units) {
        setId(id);
        setType(type);
        setTitle(title);
        setPrice(price);
        setUnits(units);
    }

    public synchronized void updateUnits(int units) {
        this.units += units;
    }

    public synchronized void updatePlato(String type, String title, float price, int units) {
        setType(type);
        setTitle(title);
        setPrice(price);
        setUnits(units);
    }

    public synchronized void updatePlato(Plato plato) {
        updatePlato(plato.getType(), plato.getTitle(), plato.getPrice(), plato.getUnits());
    }
}
