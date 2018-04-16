package servidor.network;

import json.io.JsonIO;

import java.io.IOException;

public class Network {

    public static final String SERVER_IP = "127.0.0.1";
    public static final Integer ENTRY_SERVER_PORT = 31415;
    public static final Integer RESERVA_SERVER_PORT = 31416;

    /*public static String SERVER_IP ;
    public static Integer ENTRY_SERVER_PORT;
    public static Integer RESERVA_SERVER_PORT;*/

    public Network() {

        try {
            Network network = (Network) JsonIO.readJson(Network.class, "/data/json/server/server_config.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
