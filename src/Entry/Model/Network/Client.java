package Entry.Model.Network;

import Entry.Model.Exception.NotEnoughTableException;
import Network.Reserva.ReservaRequest;
import Network.Reserva.ReservaResponse;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * Client to comunicate to the server to verify data
 */
public class Client {

    /**
     * Socket to cominicate to the server
     */
    private Socket socket;

    /**
     * Object output to the server 
     */
    private ObjectOutputStream oos;

    /**
     * Object input from the server
     */
    private ObjectInputStream ois;

    /**
     * Creates a new client instance based on the ip and the port of the server
     * @param ip the ip of the server
     * @param port the port of the server
     * @throws IOException Exception thrown if the connection to the server cannot be established
     */
    @JsonCreator
    public Client(@JsonProperty("ip") String ip,@JsonProperty("port") int port) throws IOException {
        socket = new Socket(ip, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Checks if the data passed from the client to the server, if correct, returns the password for the client,
     * if wrong throws an exception
     * @param numOfPeople the number of people in the table
     * @param date the date of the reservation
     * @param name the name of the reservation
     * @return the password of the reservation
     * @throws NotEnoughTableException If there is not enough tables to fit this reservation, this exception is thrown
     * @throws IOException If there is an error writing or reading from the server this exception is thrown
     */
    public String getPassword(int numOfPeople,Date date,String name) throws NotEnoughTableException, IOException, ClassNotFoundException {
        ReservaRequest request = new ReservaRequest(name,date,numOfPeople);
        oos.writeObject(request);

        ReservaResponse response = (ReservaResponse) ois.readObject();
        if(response.isAvailable()){
            return response.getPassword();
        }
        else{
            throw new NotEnoughTableException();
        }
    }

    /**
     * Disconnects the client
     */
    public void disconnect() throws IOException {
        ois.close();
        oos.close();
        socket.close();
    }
}
