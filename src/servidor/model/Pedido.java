package servidor.model;

import servidor.Main;
import servidor.model.Database.BBDDManager;

import java.util.LinkedList;

/**
 * Clase que representa un pedido.
 * Tiene asociado una reserva y dos gestores de platos correspondientes a los pendientes y procesados.
 */
public class Pedido {

    private Reserva reserva;
    private PlatosManager platosPendientes;
    private PlatosManager platosProcesados;

    /**
     * Constructor que inicializa los atributos del Pedido.
     */
    public Pedido() {
        reserva = new Reserva();
        platosPendientes = new PlatosManager();
        platosProcesados = new PlatosManager();
    }

    /**
     * Constructor que inicializa todos los atributos y asigna una reserva a dicho pedido.
     * @param reserva resrva que se recibe para asignar.
     */
    public Pedido(Reserva reserva) {
        this();
        setPedido(reserva);
    }

    /**
     * Permite asignar una reserva al pedido.
     * @param reserva que se recibe para asignar.
     */
    public void setPedido(Reserva reserva) {
        setReserva(reserva);
    }

    /**
     * Permite asignar una reserva a la reserva del pedido.
     * @param reserva que se recibe para asignar.
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    /**
     * Metodo que permite añadir platos pendientes al pedido.
     * @param platos que se reciben para asignar al pedido.
     */
    public void addPlatosPendientes(LinkedList<Plato> platos){

        if (platosPendientes.getPlatos().size() == 0) {
            if (platos.size() != 0){
                platosPendientes.addPlatos(platos);
            }
        } else {
            BBDDManager manager = BBDDManager.getInstance(Main.BBDD);
            manager.connect();
            for (Plato plato : platos) {
                int found = -1;
                for (int j = 0; j < platosPendientes.getPlatos().size(); j++) {
                    if (plato.getId() == platosPendientes.getPlatos().get(j).getId()) {
                        found = j;
                        break;
                    }
                }

                if (found != -1) {
                    //Update Existing Plato Units
                    platosPendientes.getPlatos().get(found).updateUnits(plato.getUnits());
                    String query = "UPDATE Plato as p SET contador=(SELECT p1.contador FROM Plato as p1 WHERE p1.id_plato" + plato.getId()+") +   1 " +
                            "WHERE p.id_plato = "+plato.getId();
                    manager.modificationQuery(query);
                } else {
                    //Add New Plato
                    platosPendientes.addPlato(plato);
                }
            }
            manager.disconnect();
        }
    }

    /**
     * Metodo que permite conocer el numero de platos pendientes.
     * @return el numero de platos pendiente.
     */
    public PlatosManager getPlatosPendientes() {
        return platosPendientes;
    }

    /**
     * Permite obtener la reserva del pedido.
     * @return la reserva del pedido.
     */
    public Reserva getReserva() {
        return reserva;
    }

    /**
     * Metodo que permite obtener los platos procesados del pedido.
     * @return el gestor de platos procesados.
     */
    public PlatosManager getPlatosProcesados() {
        return platosProcesados;
    }

    /**
     * Metodo que permite pasar un plato de los platos pendientes a platos procesados.
     * @param id del plato que se recibe para servir.
     */
    public void servirPlato(long id) {
        Plato plato = platosPendientes.getPlato(id);
        platosProcesados.addPlato(plato);
        platosPendientes.removePlato(id);
    }

    /**
     *  Permite conocer el numero total de platos del pedido.
     * @return el numero total de platos (pendientes + procesasdos)
     */
    public int getTotalPlatos() {
        return platosPendientes.getPlatos().size() + platosProcesados.getPlatos().size();
    }

    /**
     * Permite convertir un pedido a un formato compatible con el JTable utilizado para representar los pedidos.
     * @return un objeto destinado a ir al JTable de la vista de pedidos.
     */
    public Object[] toObjectArray(){
        Object[] data = new Object[6];
        data[0] = reserva.getId();
        data[1] = reserva.getName();
        data[2] = reserva.getDate();
        data[3] = reserva.getAmount();
        data[4] = getTotalPlatos();
        data[5] = platosPendientes.getPlatos().size();
        return data;
    }

}
