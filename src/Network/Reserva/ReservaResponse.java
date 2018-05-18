package Network.Reserva;

import Network.Trama;

/**
 * Response for a reservation
 */
public class ReservaResponse extends Trama {

    /**
     * UID to avoid conflicts with the Response
     */
    private static final long serialVersionUID = 54321;

    /**
     * The password for the reservation
     */
    private final String password;

    /**
     * If there is a reservation or not
     */
    private final boolean available;

    /**
     * Creates a new response from the params
     * @param password the password of the reservation
     * @param available if the reservation could be made
     */
    public ReservaResponse(String password, boolean available) {
        this.password = password;
        this.available = available;
    }

    /**
     * returns the password of the reservation
     * @return the password of the reservation
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns if there reservation could be made
     * @return if the reservation could be made.
     */
    public boolean isAvailable() {
        return available;
    }
}
