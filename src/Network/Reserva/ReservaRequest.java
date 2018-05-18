package Network.Reserva;

import Network.Trama;

import java.util.Date;

/**
 * Class that identifies the request for a reservation
 */
public class ReservaRequest extends Trama {

    /**
     * UID to avoid conflicts with the Response
     */
    private static final long serialVersionUID = 12345;

    /**
     * The name of the reservee
     */
    private final String name;

    /**
     * The date of the reservation
     */
    private final Date date;

    /**
     * The amount of people in the table
     */
    private final int amount;

    /**
     * Creates a new instance of the request for params
     * @param name the name of the reservee
     * @param date the date of the reservation
     * @param amount the amount of people in a table
     */
    public ReservaRequest(String name, Date date, int amount) {

        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    /**
     * Returns the name of the reservation
     * @return the name of the reservation
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the date of the reservation
     * @return the name of the reservation
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the amount of people in the reservation
     * @return the amount of people in the reservation
     */
    public int getAmount() {
        return amount;
    }
}
