package servidor.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
            String idMesa = jsonMesa.get("id").getAsString();
            int numComensales = jsonMesa.get("numCOmensales").getAsInt();
            Mesa nuevaMesa = new Mesa(idMesa, numComensales);
            addMesa(nuevaMesa);
        }
    }
}
