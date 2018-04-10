package servidor.model;

import json.io.JsonIO;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class PlatosManager {

    private LinkedList<Plato> platos;

    public PlatosManager() {

        platos = new LinkedList<>();

        try {
            Plato[] pls = (Plato[]) JsonIO.readJson(Plato[].class, "/data/json/platos.json");
            platos.addAll(Arrays.asList(pls));

            for (Plato plato : platos) {
                plato.setId(SerialGenerator.getId().toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Plato getPlato(int index) {
        if(platos.isEmpty()) {
            System.out.println("No hay platos en el sistema");
            return null;
        }else {
            return platos.get(index);
        }
    }

    public void addPlato(Plato plato) {
        platos.add(plato);
    }

    public void addPlato(String id, String type, String title, String price, String units) {
        Plato plato = new Plato(id, type, title, price, units);
        platos.add(plato);
    }

    public LinkedList<Plato> getPlatos() {
        return platos;
    }

}
