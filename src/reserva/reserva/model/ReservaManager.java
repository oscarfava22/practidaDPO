package reserva.reserva.model;

public class ReservaManager {

    private Plat[] plats;

    public ReservaManager () {

        //Omplir array de plats. Aquest ha de mesurar tant com plats hi hagi.
        plats = new Plat[22];
    }

    public int getNumPlats () {
        return plats.length;
    }

}
