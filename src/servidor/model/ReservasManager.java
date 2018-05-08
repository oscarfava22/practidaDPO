package servidor.model;

import Network.Reserva.ReservaRequest;
import Network.Reserva.ReservaResponse;
import json.io.JsonIO;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

public class ReservasManager {

    private LinkedList<Reserva> reservas;


    public ReservasManager() {
        reservas = new LinkedList<>();

        //try {
            //Reserva[] reservas = (Reserva[]) JsonIO.readJson(Reserva[].class, "/data/json/reservas.json");
            //this.reservas.addAll(Arrays.asList(reservas));

            System.out.println("Num Reservas: " + this.reservas.size());

            for (Reserva reserva : reservas) {
                reserva.setId(SerialGenerator.getResrvaId());
                System.out.println(reserva.toString());
            }

        //} catch (IOException e) {
          //  e.printStackTrace();
        //}
    }

    public void addReservas(LinkedList<Reserva> reservas) {
        this.reservas.addAll(reservas);
    }

    public void addReserva() {
        reservas.add(new Reserva(1,"Alex", new Date(), 4));
    }

    public LinkedList<Reserva> getReservas() {
        return reservas;
    }

    public ReservaResponse verifyRequest(ReservaRequest reservaRequest) {
        ReservaResponse response = null;

        if (checkAvailability()) {
            response = new ReservaResponse("Hola",true);
        } else {
            response = new ReservaResponse("", false);
        }

        return response;
    }

    public boolean checkAvailability() {
        boolean ok = true;

        //TODO CHECK AVAILABILITY

        return ok;
    }
}
