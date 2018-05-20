package servidor.model.Database;

import java.sql.Time;
import java.util.Date;

public class InfoResultSetReserva {
    /**
     * Atributos de la clase
     */
    private int idReserva;
    private int idMesa;
    private String password;
    private String nombre;
    private Date date;
    private Time time;

    /**
     * Constructor de la clase con parámetros
     * @param idReserva Id de la reserva
     * @param idMesa Id de la mesa reservada
     * @param password Password de la reserva
     * @param date Fecha
     * @param time Hora
     */
    public InfoResultSetReserva(int idReserva, int idMesa, String password, Date date, Time time) {
        this.idReserva = idReserva;
        this.idMesa = idMesa;
        this.password = password;
        this.nombre = null;
        this.date = date;
        this.time = time;
    }

    /**
     * Getters & Setters
     */
    public int getIdReserva() {
        return idReserva;
    }
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
    public int getIdMesa() {
        return idMesa;
    }
    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Time getTime() {
        return time;
    }
    public void setTime(Time time) {
        this.time = time;
    }

    /**
     * To String function
     * @return The value of the result set in a string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Id reserva: ");
        builder.append(Integer.toString(idReserva));

        builder.append(System.lineSeparator());

        builder.append("Id mesa. ");
        builder.append(Integer.toString(idMesa));

        builder.append(System.lineSeparator());

        builder.append("Password: ");
        builder.append(password);

        builder.append(System.lineSeparator());

        builder.append("Date: ");
        builder.append(date);

        builder.append(System.lineSeparator());

        builder.append("Time: ");
        builder.append(time);

        builder.append(System.lineSeparator());

        return builder.toString();
    }
}
