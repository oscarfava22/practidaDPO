package servidor.model;

import json.io.JsonIO;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class PlatosManager {

    private LinkedList<Plato> platos;
    private String[] productsTypes = { "Entrante", "Plato Principal", "Postre", "Bebidas" };

    public PlatosManager() {

        platos = new LinkedList<>();

        try {
            Plato[] pls = (Plato[]) JsonIO.readJson(Plato[].class, "/data/json/platos2.json");
            platos.addAll(Arrays.asList(pls));

            System.out.println("Num Platos: " + platos.size());

            for (Plato plato : platos) {
                plato.setId(SerialGenerator.getProductId());
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

    public void addPlato(long id, String type, String title, float price, int units) {
        Plato plato = new Plato(id, type, title, price, units);
        platos.add(plato);
    }

    public LinkedList<Plato> getPlatos() {
        return platos;
    }

    public void updatePlato(Plato plato) {

        for(Plato pl : platos) {
            if (pl.getId() == plato.getId()) {
                pl.updatePlato(plato);
            }
        }
    }

    public void removePlato(long id) {

        for(int i = 0; i < platos.size(); i++) {
            if (platos.get(i).getId() == id){
                platos.remove(i);
            }
        }
    }

}
