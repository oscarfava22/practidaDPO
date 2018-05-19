package servidor.model;

import servidor.Main;
import servidor.model.Database.BBDDManager;

import java.util.LinkedList;

/**
 *
 */
public class Pedido {

    private Reserva reserva;
    private PlatosManager platosPendientes;
    private PlatosManager platosProcesados;

    /**
     *
     */
    public Pedido() {
        reserva = new Reserva();
        platosPendientes = new PlatosManager();
        platosProcesados = new PlatosManager();
    }

    /**
     *
     * @param reserva
     */
    public Pedido(Reserva reserva) {
        this();
        setPedido(reserva);
    }

    /**
     *
     * @param reserva
     */
    public void setPedido(Reserva reserva) {
        setReserva(reserva);
    }

    /**
     *
     * @param reserva
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    /**
     *
     * @param platos
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
     *
     * @return
     */
    public PlatosManager getPlatosPendientes() {
        return platosPendientes;
    }

    /**
     *
     * @return
     */
    public Reserva getReserva() {
        return reserva;
    }

    /**
     *
     * @return
     */
    public PlatosManager getPlatosProcesados() {
        return platosProcesados;
    }

    /**
     *
     * @param id
     */
    public void servirPlato(long id) {
        Plato plato = platosPendientes.getPlato(id);
        platosProcesados.addPlato(plato);
        platosPendientes.removePlato(id);
    }

    /**
     *
     * @return
     */
    public int getTotalPlatos() {
        return platosPendientes.getPlatos().size() + platosProcesados.getPlatos().size();
    }

    /**
     *
     * @return
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
