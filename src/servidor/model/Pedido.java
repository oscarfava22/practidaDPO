package servidor.model;

import java.util.LinkedList;

public class Pedido {

    private Reserva reserva;
    private Mesa mesa;
    private LinkedList<Plato> platosPendientes;
    private LinkedList<Plato> platosProcesados;

    private PlatosManager platosPendientes1;
    private PlatosManager platosProcesados1;

    public Pedido() {
        reserva = new Reserva();
        mesa = new Mesa();
        platosPendientes = new LinkedList<>();
        platosProcesados = new LinkedList<>();
        platosPendientes1 = new PlatosManager();
        platosProcesados1 = new PlatosManager();
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
        this.platosPendientes1.setPlatos(platosPendientes);
    }

    public PlatosManager getPlatosPendientes() {
        return platosPendientes1;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setPlatosProcesados(LinkedList<Plato> platosProcesados) {
        this.platosProcesados1.setPlatos(platosProcesados);
    }

    public PlatosManager getPlatosProcesados() {
        return platosProcesados1;
    }

    public void servirPlato(long id) {
        Plato plato = platosPendientes1.getPlato(id);
        platosProcesados1.addPlato(plato);
        platosPendientes1.removePlato(id);
    }

}
