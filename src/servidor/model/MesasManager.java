package servidor.model;

import java.util.Comparator;
import java.util.LinkedList;

public class MesasManager {

    //Atributos
    private LinkedList<Mesa> mesas;

    //Constructor
    public MesasManager() {
        mesas = new LinkedList<>();
    }

    //Getters & Setters
    public LinkedList<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(LinkedList<Mesa> mesas) {
        this.mesas = mesas;
    }

    //Funciones & Métodos
    public void addMesa(Mesa nuevaMesa){
        mesas.add(nuevaMesa);
        mesas.sort(new Comparator<Mesa>() {
            @Override
            public int compare(Mesa o1, Mesa o2) {
                int numComensales1 = o1.getNumComensales();
                int numComensales2 = o2.getNumComensales();
                return Integer.compare(numComensales1, numComensales2);
            }
        });
    }
}
