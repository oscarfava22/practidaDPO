package servidor.network;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import json.io.JsonIO;
import servidor.model.Constants;

import java.io.IOException;

/**
 * Clase que contine la configuracion de la IP del servidor y los puertos habilitados para los clientes.
 */
public class Network {

    private final  String SERVER_IP;
    private  final Integer ENTRY_SERVER_PORT;
    private  final Integer RESERVA_SERVER_PORT;

    /**
     * Constructor que lee el archivo de configuracion.
     */
    @JsonCreator
    public Network(@JsonProperty("SERVER_IP")String ip,@JsonProperty("ENTRY_SERVER_PORT_port") int port, @JsonProperty("RESERVA_SERVER_PORT") int port2) {
        this.SERVER_IP = ip;
        this.ENTRY_SERVER_PORT = port;
        this.RESERVA_SERVER_PORT = port2;
    }

    public String getSERVER_IP() {
        return SERVER_IP;
    }

    public Integer getENTRY_SERVER_PORT() {
        return ENTRY_SERVER_PORT;
    }

    public Integer getRESERVA_SERVER_PORT() {
        return RESERVA_SERVER_PORT;
    }
}
