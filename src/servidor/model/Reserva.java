package servidor.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
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
     *
     */
    public Reserva () {
    }

    /**
     *
     * @param id
     * @param name
     * @param date
     * @param amount
     * @param password
     * @param state
     * @param mesa
     */
    public Reserva(long id, String name, Date date, int amount, String password, int state,Mesa mesa) {
        setReserva(id, name, date, amount, password, state,mesa);
    }

    /**
     *
     * @param id
     * @param name
     * @param date
     * @param amount
     * @param password
     * @param state
     * @param mesa
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
     *
     * @return
     */
    public Mesa getMesa() {
        return mesa;
    }

    /**
     *
     * @param mesa
     */
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public int getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return (id + "%" + name + "%" + date + "%" + amount + "%" + password + "%" + state);
    }

}
