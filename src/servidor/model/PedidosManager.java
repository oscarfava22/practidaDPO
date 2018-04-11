package servidor.model;

import java.util.LinkedList;

public class PedidosManager {

    private LinkedList<Pedido> pedidos;

    public PedidosManager() {
        pedidos = new LinkedList<>();

    }

    public LinkedList<Pedido> getPedidos() {
        return pedidos;
    }
}
