package servidor.model;

import java.util.LinkedList;

/**
 * Gestor de pedidos formado por una lista de "Pedido" y metodos para poder realizar modificaciones personalizadas.
 */
public class PedidosManager {

    private LinkedList<Pedido> pedidos;

    /**
     * Contructor que inicializa la lista de pedidos.
     */
    public PedidosManager() {
        pedidos = new LinkedList<>();
    }

    /**
     * Permite añadir un pedido al gestor.
     * @param pedido que se recibe para añadir al gestor.
     */
    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    /**
     * Metodo que devuelve un Pedido segun el id recibido.
     * @param id que se recibe para identificar el pedido.
     * @return el Pedido encontrado o null en caso de no encontrarlo.
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
     * Metodo que devuelve un Pedido segun el nombre de la Reserva.
     * @param name que se recibe para identificar el pedido.
     * @return el Pedido encontrado o null en caso de no encontrarlo.
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
     * Metodo para tener acceso a todos los pedidos disponibles en el gestor.
     * @return la lista de pedidos disponibles.
     */
    public LinkedList<Pedido> getPedidos() {
        return pedidos;
    }

}
