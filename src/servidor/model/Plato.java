package servidor.model;

public class Plato {

    private String id;
    private String type;
    private String title;
    private String price;
    private String units;

    public Plato() {

    }

    public Plato(String id, String type, String title, String price, String units) {
        setId(id);
        setType(type);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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