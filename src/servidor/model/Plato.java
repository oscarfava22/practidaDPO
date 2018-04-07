package servidor.model;

public class Plato {

    private String id;
    private String title;
    private String price;
    private String units;

    public Plato() {

    }

    public Plato(String id, String title, String price, String units) {
        setId(id);
        setTitle(title);
        setPrice(price);
        setUnits(units);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
