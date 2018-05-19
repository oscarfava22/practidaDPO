package servidor.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que representa uan reserva del programa.
 * La reserva se caracteriza por los siquiente estados (state):
 *   0: reserva creada.
 *   1: reserva activada (se ha conectado un cliente con el nombre y contraseña correctos)
 *   2: se han recibido platos como pedido por parte del cliente.
 *   3: se ha pagado el pedido y se ha finalizado la reserva.
 */
public class Reserva implements Serializable {

    private long id;
    private String name;
    private Date date;
    private int amount;
    private String password;
    private int state = 0;
    private Mesa mesa;

    /**
     * Constructor por defecto
     */
    public Reserva () {
    }

    /**
     * Constructor que inicializa todos los atributos de la Reserva
     * @param id de la reserva.
     * @param name de la reserva.
     * @param date de la reserva.
     * @param amount de la reserva.
     * @param password de la reserva.
     * @param state de la reserva.
     * @param mesa de la reserva.
     */
    public Reserva(long id, String name, Date date, int amount, String password, int state, Mesa mesa) {
        setReserva(id, name, date, amount, password, state, mesa);
    }

    /**
     * Método que permite inicializara todos los atributos de una reserva.
     * @param id de la reserva.
     * @param name de la reserva.
     * @param date de la reserva.
     * @param amount de la reserva.
     * @param password de la reserva.
     * @param state de la reserva.
     * @param mesa de la reserva.
     */
    public void setReserva(long id, String name, Date date, int amount, String password, int state,Mesa mesa) {
        setId(id);
        setName(name);
        setDate(date);
        setAmount(amount);
        setPassword(password);
        setState(state);
        setMesa(mesa);
    }

    /**
     * Permite obtener la mesa asociada a la reserva.
     * @return la mesa de la reserva.
     */
    public Mesa getMesa() {
        return mesa;
    }

    /**
     * Permite asociar una mesa a la reserva.
     * @param mesa que se recibe para asociar.
     */
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    /**
     * Permite obtener el id de la reserva.
     * @return el id de la reserva.
     */
    public long getId() {
        return id;
    }

    /**
     * Permite asignar un id a la reserva.
     * @param id para asignar a la reserva.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Permite obtener el nombre la reserva.
     * @return el nombre de la reserva.
     */
    public String getName() {
        return name;
    }

    /**
     * Permite asignar un nombre a la reserva.
     * @param name para asignar a la reserva.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Permite obtener la fecha de la reserva.
     * @return la fecha de la reserva.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Permite asignar una fecha a la reserva.
     * @param date para asignar a la reserva.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Permite obtener el numero de comensales de la reserva.
     * @return nº de comensales de la reserva.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Permite asignar un numero de comensales a la reserva.
     * @param amount numero de comensales.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Permite obtener la contraseña de la reserva.
     * @return la contraseña de la reserva.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Permite asignar una contraseña a la reserva.
     * @param password contraseña para asignar a la reserva.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Permite conocer el estado de la reserva.
     * @return el estado de la reserva.
     */
    public int getState() {
        return state;
    }

    /**
     * Permite asignar un estado a la reserva.
     * @param state estado para asignar a la reserva.
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * Permite convertir los atributos de la Reserva en una cadena de texto con atributos especiales para poder
     * procesar posteriormente los datos.
     * @return una cadena de texto con todos los atributos separados por el caracter "%"
     */
    @Override
    public String toString() {
        return (id + "%" + name + "%" + date + "%" + amount + "%" + password + "%" + state);
    }

}
