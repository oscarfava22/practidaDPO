package servidor.network;

import json.io.JsonIO;
import servidor.model.Constants;

import java.io.IOException;

/**
 * Clase que contine la configuracion de la IP del servidor y los puertos habilitados para los clientes.
 */
public class Network {

    public static final String SERVER_IP = "127.0.0.1";
    public static final Integer ENTRY_SERVER_PORT = 31415;
    public static final Integer RESERVA_SERVER_PORT = 31417;

    /**
     * Constructor que lee el archivo de configuracion.
     */
    public Network() {
        try {
            Network network = (Network) JsonIO.readJson(Network.class, Constants.serverConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
