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
        loadPlatos();
    }

    public void addPlato(Plato plato) {
        //TODO ADD PLATO TO BBDD
        platos.add(plato);
    }

    public void addPlato(long id, String type, String title, float price, int units) {
        Plato plato = new Plato(id, type, title, price, units);
        platos.add(plato);
    }

    public void updatePlato(long id, String type, String title, float price, int units) {
        //TODO UPDATE PLATO IN BBDD
        for(Plato pl : platos) {
            if (pl.getId() == id) {
                pl.updatePlato(type, title, price, units);
            }
        }
    }

    public void updatePlato(Plato plato) {
        updatePlato(plato.getId(), plato.getType(), plato.getTitle(), plato.getPrice(), plato.getUnits());
    }

    public void removePlato(long id) {
        //TODO REMOVE PLATO FROM BBDD
        for(int i = 0; i < platos.size(); i++) {
            if (platos.get(i).getId() == id){
                platos.remove(i);
            }
        }
    }

    public String[] getProductTypes() {
        return productsTypes;
    }

    public Plato getPlato(int index) {
        //TODO GET PLATO FROM BBDD
        if(platos.isEmpty()) {
            System.out.println("No hay platos en el sistema");
            return null;
        }else {
            return platos.get(index);
        }
    }

    public LinkedList<Plato> getPlatos() {
        //TODO GET PLATOS FROM BBDD
        return platos;
    }

    public void loadPlatos() {
        //TODO Load from BBDD

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
}
