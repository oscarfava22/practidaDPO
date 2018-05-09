package servidor.model;

import java.util.LinkedList;

public class PedidosManager {

    private LinkedList<Pedido> pedidos;

    public PedidosManager() {
        pedidos = new LinkedList<>();
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Pedido getPedidoByReservaId(long id) {

        for (Pedido pedido : pedidos) {
            if(pedido.getReserva().getId() == id) {
                return pedido;
            }
        }

        return null;
    }

    public Pedido getPedidoByReservaName(String name) {
        for (Pedido pedido : pedidos) {
            if(pedido.getReserva().getName().equals(name)) {
                return pedido;
            }
        }

        return null;
    }

    public LinkedList<Pedido> getPedidos() {
        return pedidos;
    }



}
