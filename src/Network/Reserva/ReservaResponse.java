package Network.Reserva;

import Network.Trama;
//TODO DOCUMENTAR
public class ReservaResponse extends Trama {
    private static final long serialVersionUID = 54321;

    private final String password;
    private final boolean available;

    public ReservaResponse(String password, boolean available) {
        this.password = password;
        this.available = available;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAvailable() {
        return available;
    }
}
