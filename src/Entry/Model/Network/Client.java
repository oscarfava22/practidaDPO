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

public class Client {
    private Socket socket;
    private DataOutputStream dos;
    private ObjectOutputStream oos;
    private DataInputStream dis;

    @JsonCreator
    public Client(@JsonProperty("ip") String ip,@JsonProperty("port") int port) throws IOException {
        socket = new Socket(ip,port);
        dos = new DataOutputStream(socket.getOutputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
    }

    public String getPassword(int numOfPeople,Date fecha,String name) throws NotEnoughTableException, IOException {
        dos.write(numOfPeople);
        dos.writeUTF(name);
        oos.writeObject(fecha);
        if(dis.readBoolean()){
            return dis.readUTF();
        }
        else{
            throw new NotEnoughTableException();
        }
    }
}
