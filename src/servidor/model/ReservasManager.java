package servidor.model;

import Network.Reserva.ReservaRequest;
import Network.Reserva.ReservaResponse;

import java.util.LinkedList;

public class ReservasManager {

    private LinkedList<Reserva> reservas;


    public ReservasManager() {
        reservas = new LinkedList<>();
        loadReservas();

    }

    public void loadReservas() {
        /*try {
        Reserva[] reservas = (Reserva[]) JsonIO.readJson(Reserva[].class, "/data/json/reservas.json");
        this.reservas.addAll(Arrays.asList(reservas));

        System.out.println("Num Reservas: " + this.reservas.size());

        for (Reserva reserva : reservas) {
            reserva.setId(SerialGenerator.getReservaId());
            System.out.println(reserva.toString());
        }

        } catch (IOException e) {
          e.printStackTrace();
        }*/

        //TODO Connect to BBDD and load data
    }

    public void addReservas(LinkedList<Reserva> reservas) {
        this.reservas.addAll(reservas);
    }

    public void addReserva(Reserva reserva) {
        reservas.add(reserva);
        //TODO Add Reserva to BBDD
    }

    public void removeReserva() {

    }

    public LinkedList<Reserva> getReservas() {
        return reservas;
    }

    public ReservaResponse verifyRequest(ReservaRequest reservaRequest) {
        ReservaResponse response = null;

        if (checkAvailability()) {
            Reserva reserva = new Reserva(SerialGenerator.getReservaId() ,reservaRequest.getName(), reservaRequest.getDate(), reservaRequest.getAmount(), "Pass", 0);
            addReserva(reserva);
            System.out.println("New Reserva Created: ");
            System.out.println("\t" + reserva.toString());

            response = new ReservaResponse(reserva.getPassword(),true);
        } else {
            response = new ReservaResponse("", false);
        }

        return response;
    }

    public boolean checkAvailability() {
        boolean ok = true;

        //TODO CHECK AVAILABILITY VIA BBDD

        return ok;
    }

    public Reserva searchReserva(String name, String password) {

        //TODO SEARCH RESERVA VIA BBDD, IF FOUND UPDATE RESERVA STATE

        for(Reserva reserva : reservas) {
            if(reserva.getName().equals(name) && reserva.getPassword().equals(password)) {
                if(reserva.getState() == 0) {
                    reserva.setState(1);
                    return reserva;
                }
                break;
            }
        }

        return null;
    }
}
