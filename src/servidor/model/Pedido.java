package servidor.model;

import java.util.LinkedList;

public class Pedido {

    private Reserva reserva;
    private Mesa mesa;
    private PlatosManager platosPendientes;
    private PlatosManager platosProcesados;

    public Pedido() {
        reserva = new Reserva();
        mesa = new Mesa();
        platosPendientes = new PlatosManager();
        platosProcesados = new PlatosManager();
    }

    public Pedido(Reserva reserva, Mesa mesa) {
        this();
        setPedido(reserva, mesa);
    }

    public void setPedido(Reserva reserva, Mesa mesa) {
        setReserva(reserva);
        setMesa(mesa);
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void setPlatosPendientes(LinkedList<Plato> platosPendientes) {
        this.platosPendientes.setPlatos(platosPendientes);
    }

    public void addPlatosPendientes(LinkedList<Plato> platos){

        if (platosPendientes.getPlatos().size() == 0) {
            System.out.println("First ADD");
            if (platos.size() != 0){
                platosPendientes.addPlatos(platos);
            }
        } else {
            for (int i = 0; i < platos.size(); i++) {
                int found = -1;
                for (int j = 0; j < platosPendientes.getPlatos().size(); j++) {
                    if (platos.get(i).getId() == platosPendientes.getPlatos().get(j).getId()) {
                        found = j;
                        break;
                    }
                }

                if (found != -1) {
                    //Update Existing Plato Units
                    platosPendientes.getPlatos().get(found).updateUnits(platos.get(i).getUnits());
                } else {
                    //Add New Plato
                    platosPendientes.addPlato(platos.get(i));
                }
            }
        }

    }

    public PlatosManager getPlatosPendientes() {
        return platosPendientes;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setPlatosProcesados(LinkedList<Plato> platosProcesados) {
        this.platosProcesados.setPlatos(platosProcesados);
    }

    public PlatosManager getPlatosProcesados() {
        return platosProcesados;
    }

    public void servirPlato(long id) {
        Plato plato = platosPendientes.getPlato(id);
        platosProcesados.addPlato(plato);
        platosPendientes.removePlato(id);
    }

    public int getTotalPlatos() {
        return platosPendientes.getPlatos().size() + platosProcesados.getPlatos().size();
    }

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
