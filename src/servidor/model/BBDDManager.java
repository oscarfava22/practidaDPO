package servidor.model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The manager of the bbdds
 */
public class BBDDManager {

    /**
     * User of the bbdd default root
     */
    private static String user = "root";

    /**
     * Password of the bbdd default nothing
     */
    private static String password = "";

    /**
     *
     */
    private static String ip = "localhost";
    private static int port = 3306;

    private static BBDDManager instance;

    private  String url = "jdbc:mysql://";
    private  Connection connection;
    private  Statement statement;

    public BBDDManager getInstance(String bbdd){
        if(instance==null){
            instance = new BBDDManager(bbdd,ip,port);
        }
        return instance;
    }

    public static void setUsername(String username){
        if(instance==null){
            user = username;
            return;
        }
        throw new IllegalStateException("The instance already exists");
    }

    public static void setPassword(String password){
        if(instance==null){
            BBDDManager.password=password;
            return;
        }
        throw new IllegalStateException("The instance already exists");
    }

    public static void setIp(String ip){
        if(instance==null){
            BBDDManager.ip = ip;
            return;
        }
        throw new IllegalStateException("The instance already exists");
    }

    public static void setPort(int port){
        if(instance==null){
            BBDDManager.port=port;
            return;
        }
        throw new IllegalStateException("The instance already exists");
    }

    public static void recreateBBDD(){
        if(instance!=null){
            instance.disconect();
            instance=null;
            return;
        }
        throw new IllegalStateException("The instance doesn't exists");
    }

    private BBDDManager(String bbdd, String ip, int port) {
        this.url += ip;
        this.url += ":"+port+"/";
        this.url += bbdd;
    }

    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Connection");
            connection = (Connection) DriverManager.getConnection(url,user,password);
            if(connection!=null){
                System.out.println("Connection ok");
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database "+url+" with user: "+ BBDDManager.user+" and password: "
                    + BBDDManager.password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void modificationQuery(String query){
        try {
            statement = (Statement)connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Error updating the bbdd contents");
        }
    }

    public ResultSet readQuery(String query){
        try {
            statement = (Statement)connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Error reading bbdd");
            return null;
        }

    }


    public void disconect(){
        try {
            connection.close();
            System.out.println("Disconected");
        } catch (SQLException e) {
            System.err.println("Error closing the bbdd");
        }
    }
}
