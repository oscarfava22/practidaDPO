package servidor.model;

import servidor.Main;
import servidor.model.Database.BBDDManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Gestor de platos. Contiene una lista de platos y acceso al gestor de bases de datos.
 */
public class PlatosManager {

    private LinkedList<Plato> platos;
    private BBDDManager manager;

    /**
     * Contructor que inicializa la lista de platos y la conexion con la base de datos.
     */
    public PlatosManager() {
        manager = BBDDManager.getInstance(Main.BBDD);
        platos = new LinkedList<>();
    }

    /**
     * Método para cargar platos en el gestor.
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
     * Permite añadir nuevos platos en el gestor.
     * @param plato que se recibe para añadir al gestor.
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
     * Permite añadir nuevos platos en el gestor.
     * @param platos que se reciben para añadir en el gestor.
     */
    public void addPlatos(LinkedList<Plato> platos) {
        for(Plato plato:platos){
            addPlato(plato);
        }
    }

    /**
     * Método que permite actualizar un plato.
     * @param id del plato.
     * @param type del plato.
     * @param title del plato.
     * @param price del plato.
     * @param units del plato.
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
     * Permite eliminar un plato del gestor segun su id.
     * @param id que identifica al plato que se quiere eliminar.
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
     * Permite obtener un plato segun su id.
     * @param id del plato.
     * @return el plato si se ha encontrado en el gestor, null en caso contrario.
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
     * Permite obtener todos los platos en el gestor.
     * @return lista de todos los platos.
     */
    public LinkedList<Plato> getPlatos() {
        return platos;
    }

    /**
     * Permite obtener todos los platos cuyas unidades son superiores a 0.
     * Este método es utilizado para enviar los platos al cliente, de manera que solo recibira los disponibles realmente.
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
