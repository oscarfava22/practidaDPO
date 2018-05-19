package servidor.model;

import java.util.LinkedList;

/**
 *
 */
public class PedidosManager {

    private LinkedList<Pedido> pedidos;

    /**
     *
     */
    public PedidosManager() {
        pedidos = new LinkedList<>();
    }

    /**
     *
     * @param pedido
     */
    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    /**
     *
     * @param id
     * @return
     */
    public Pedido getPedidoByReservaId(long id) {

        for (Pedido pedido : pedidos) {
            if(pedido.getReserva().getId() == id) {
                return pedido;
            }
        }

        return null;
    }

    /**
     *
     * @param name
     * @return
     */
    public Pedido getPedidoByReservaName(String name) {
        for (Pedido pedido : pedidos) {
            if(pedido.getReserva().getName().equals(name)) {
                return pedido;
            }
        }

        return null;
    }

    /**
     *
     * @return
     */
    public LinkedList<Pedido> getPedidos() {
        return pedidos;
    }

}
