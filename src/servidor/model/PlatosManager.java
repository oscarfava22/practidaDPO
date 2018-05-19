package servidor.model;

import servidor.Main;
import servidor.model.Database.BBDDManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 */
public class PlatosManager {

    private LinkedList<Plato> platos;
    private BBDDManager manager;

    /**
     *
     */
    public PlatosManager() {
        manager = BBDDManager.getInstance(Main.BBDD);
        platos = new LinkedList<>();
    }

    /**
     *
     */
    public void loadPlatos() {

        manager.connect();
        ResultSet set = manager.readQuery("SELECT * FROM Plato;");
        if(set!=null){
            try {
                while(set.next()){
                    String  nombre = set.getString("nombre");
                    int units = set.getInt("unidades");
                    int type = set.getInt("id_tipus");
                    float price = set.getFloat("precio");
                    int id = set.getInt("id_plato");
                    Plato plato = new Plato();
                    plato.setPlato(id,String.valueOf(type),nombre,price,units);
                    platos.add(plato);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        manager.disconnect();
        /*
        try {

            Plato[] pls = (Plato[]) JsonIO.readJson(Plato[].class, "/data/json/platos.json");
            platos.addAll(Arrays.asList(pls));

            System.out.println("Num Platos: " + platos.size());

            for (Plato plato : platos) {
                plato.setId(SerialGenerator.getProductId());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    /**
     *
     * @param plato
     */
    public void addPlato(Plato plato) {
        manager.connect();
        String query = new StringBuilder().append("INSERT INTO Plato VALUES(").append(plato.getId()).append(",'")
                .append(plato.getTitle()).append("',").append(plato.getUnits()).append(",").append(plato.getType())
                .append(",").append(plato.getPrice()).append(",").append(1).append(");").toString();
        manager.modificationQuery(query);
        manager.disconnect();

        platos.add(plato);
    }

    /**
     *
     * @param platos
     */
    public void addPlatos(LinkedList<Plato> platos) {
        for(Plato plato:platos){
            addPlato(plato);
        }
    }

    /**
     *
     * @param id
     * @param type
     * @param title
     * @param price
     * @param units
     */
    public void updatePlato(long id, String type, String title, float price, int units) {
        manager.connect();
        String query = new StringBuilder().append("UPDATE Plato SET nombre='").append(title).append("', unidades=").append(units)
                .append(", id_tipus=").append(type).append(", precio=").append(price).append(" WHERE id_plato=").append(id).append(";").toString();
        manager.modificationQuery(query);
        manager.disconnect();

        for(Plato pl : platos) {
            if (pl.getId() == id) {
                pl.updatePlato(type, title, price, units);
            }
        }
    }

    /**
     *
     * @param id
     */
    public void removePlato(long id) {
        manager.connect();
        String query = new StringBuilder().append("DELETE FROM Plato WHERE id_plato=").append(id).append(";").toString();
        manager.modificationQuery(query);
        manager.disconnect();

        for(int i = 0; i < platos.size(); i++) {
            if (platos.get(i).getId() == id){
                platos.remove(i);
            }
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Plato getPlato(long id) {
        if (platos.isEmpty()) {
            System.out.println("No hay platos en el sistema");
            return null;
        } else {
            for(Plato plato : platos) {
                if (plato.getId() == id) {
                    return plato;
                }
            }
            return null;
        }
    }

    /**
     *
     * @return
     */
    public LinkedList<Plato> getPlatos() {
        return platos;
    }

    /**
     *
     * @return
     */
    public LinkedList<Plato> getAvailablePlatos() {

        LinkedList<Plato> pls = new LinkedList<>();

        for(Plato p : platos) {
            if(p.getUnits() > 0) {
                pls.add(p);
            }
        }
        return pls;
    }
}
