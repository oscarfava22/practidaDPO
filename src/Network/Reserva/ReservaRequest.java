package Network.Reserva;

import Network.Trama;

import java.util.Date;

//TODO Documentar
public class ReservaRequest extends Trama {
    private final String name;
    private final Date date;
    private final int amount;

    public ReservaRequest(String name, Date date, int amount) {

        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }
}
