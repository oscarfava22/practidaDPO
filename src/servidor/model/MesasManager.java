package servidor.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import servidor.view.GestionMesasView;

import java.util.Comparator;
import java.util.LinkedList;

public class MesasManager {

    //Atributos
    private LinkedList<Mesa> mesas;

    //Constructor
    public MesasManager(JsonObject jsonObjectFromConfigFile) {
        mesas = new LinkedList<>();
        //Obtener mesas del config.json
        getMesasFromJsonArray(jsonObjectFromConfigFile);
        for (int i = 0; i < mesas.size(); i++){
            Mesa mesa = mesas.get(i);
            System.out.println("Mesa " + i + ":");
            System.out.println("    ID: " + mesa.getId());
            System.out.println("    NUM. COMENSALES: " + mesa.getNumComensales());
        }
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

    public void getMesasFromJsonArray(JsonObject json){
        JsonArray mesasJsonArray = json.get("mesas").getAsJsonArray();
        for (int i = 0; i < mesasJsonArray.size(); i++){
            JsonObject jsonMesa = mesasJsonArray.get(i).getAsJsonObject();
            int idMesa = jsonMesa.get("id").getAsInt();
            int numComensales = jsonMesa.get("numComensales").getAsInt();
            Mesa nuevaMesa = new Mesa(idMesa, numComensales);
            addMesa(nuevaMesa);
        }
        GestionMesasView.NUMERO_MESAS = mesas.size();
    }
}
