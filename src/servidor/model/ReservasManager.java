package servidor.model;

import json.io.JsonIO;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class ReservasManager {

    private LinkedList<Reserva> reservas;


    public ReservasManager() {
        reservas = new LinkedList<>();

        try {
            Reserva[] reservas = (Reserva[]) JsonIO.readJson(Reserva[].class, "/data/json/reservas.json");
            this.reservas.addAll(Arrays.asList(reservas));

            System.out.println("Num Reservas: " + this.reservas.size());

            for (Reserva reserva : reservas) {
                reserva.setId(SerialGenerator.getResrvaId());
                System.out.println(reserva.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Reserva> getReservas() {
        return reservas;
    }
}
