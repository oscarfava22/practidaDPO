package Entry.Model.Network;

import Entry.Model.Exception.NotEnoughTableException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
     * Data output to the server
     */
    private DataOutputStream dos;

    /**
     * Object output to the server 
     */
    private ObjectOutputStream oos;
    private DataInputStream dis;

    /**
     * Creates a new client instance based on the ip and the port of the server
     * @param ip the ip of the server
     * @param port the port of the server
     * @throws IOException Exception thrown if the connection to the server cannot be established
     */
    @JsonCreator
    public Client(@JsonProperty("ip") String ip,@JsonProperty("port") int port) throws IOException {
        socket = new Socket(ip,port);
        dos = new DataOutputStream(socket.getOutputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
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
    public String getPassword(int numOfPeople,Date date,String name) throws NotEnoughTableException, IOException {
        dos.write(numOfPeople);
        dos.writeUTF(name);
        oos.writeObject(date);
        if(dis.readBoolean()){
            return dis.readUTF();
        }
        else{
            throw new NotEnoughTableException();
        }
    }
}
