package servidor.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import servidor.view.GestionMesasView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.LinkedList;

public class MesasManager {

    //Atributos
    private LinkedList<Mesa> mesas;

    //Constructor
    public MesasManager() {
        mesas = new LinkedList<>();
        //TODO: Obtener mesas de la bbdd aqui o en el initMesas (mejor init)
        //Obtener mesas del config.json
        /*try {
            getMesasFromJson();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("No s'ha pogut llegir l'arxiu .json");
        }*/
    }

    public void getMesasFromJson() throws FileNotFoundException {
        JsonParser parser = new JsonParser();

        Object object = parser.parse(new FileReader("data/json/config.json"));

        JsonObject jsonObject = (JsonObject)object;

        JsonArray jsonArrayMesas = jsonObject.get("mesas").getAsJsonArray();
        for (int i = 0; i < jsonArrayMesas.size(); i++){
            addMesa(new Mesa(jsonArrayMesas.get(i).getAsJsonObject().get("id").getAsInt(), jsonArrayMesas.get(i).getAsJsonObject().get("numComensales").getAsInt()));
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
}
