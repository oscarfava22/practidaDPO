package Network.Reserva;

import Network.Trama;
//TODO DOCUMENTAR
public class ReservaResponse extends Trama {
    private final String password;
    private final boolean error;

    public ReservaResponse(String password, boolean error) {
        this.password = password;
        this.error = error;
    }

    public String getPassword() {
        return password;
    }

    public boolean isError() {
        return error;
    }
}
