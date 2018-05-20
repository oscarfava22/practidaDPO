package servidor.model;

import java.util.Comparator;
import java.util.LinkedList;

public class MesasManager {

    /**
     * Atributos de la clase
     */
    private LinkedList<Mesa> mesas;
    private String idMesaSeleccionada;

    /**
     * Constructor de la clase
     */
    public MesasManager() {
        mesas = new LinkedList<>();
        idMesaSeleccionada = null;
    }

    /**
     * Getters & Setters
     */
    public LinkedList<Mesa> getMesas() {
        return mesas;
    }
    public void setMesas(LinkedList<Mesa> mesas) {
        this.mesas = mesas;
    }
    public String getIdMesaSeleccionada() {
        return idMesaSeleccionada;
    }
    public void setIdMesaSeleccionada(String idMesaSeleccionada) {
        this.idMesaSeleccionada = idMesaSeleccionada;
    }

    /**
     * Función para añadir una nueva mesa
     * --> Función ya no usada porque se hace cargando la base de datos entera, no es necesario ir añadiendo mesas
     * @param nuevaMesa
     */
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
