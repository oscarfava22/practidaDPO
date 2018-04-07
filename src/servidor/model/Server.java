package servidor.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    //Atributos
    private boolean isOn;
    private ServerSocket sSocket;
    private List<DedicatedServer> dServers;
    private ServerData data;

    //Constructor
    public Server() {
        this.isOn = false;
        //TODO server socket (con NetworkConfiguration)
        this.sSocket = null;
        this.dServers = new ArrayList<DedicatedServer>();
        this.data = new ServerData(getJsonObjectFromConfigFile());
    }



    //Funciones
    public JsonObject getJsonObjectFromConfigFile(){
        JsonObject dades;

        JsonParser parser = new JsonParser();
        //LLEGIR JSON I ESTRUCTURARLO A LES NOSTRES DADES
        //llegirem l'arxiu "laSallers.json" ubicat a la carpeta "src" del projecte
        FileReader file = null;
        try {
            file = new FileReader("data/json/config.json");
            Object obj = parser.parse(file);
            dades = (JsonObject)obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("No s'ha pogut llegir el fitxer de configuració");
            dades = null;
        }

        return dades;
    }

}
