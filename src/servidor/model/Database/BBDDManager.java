package servidor.model.Database;

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
     * Ip to connect to the database, default localhost
     */
    private static String ip = "localhost";

    /**
     * Port by which to connect to database default 3306
     */
    private static int port = 3306;

    /**
     * Instance of the database
     */
    private static BBDDManager instance;

    /**
     * Url to connect to the database
     */
    private  String url = "jdbc:mysql://";

    /**
     * Connection to the database
     */
    private  Connection connection;

    /**
     * The statement by which querys are made
     */
    private  Statement statement;

    /**
     * Returns the instance of the database manager
     * @param bbdd the database to connect
     * @return the instance of the manager
     */
    public static BBDDManager getInstance(String bbdd){
        if(instance==null){
            instance = new BBDDManager(bbdd,ip,port);
        }
        return instance;
    }

    /**
     * Sets username of the database
     * @param username the username of the database
     */
    public static void setUsername(String username){
        if(instance==null){
            user = username;
            return;
        }
        throw new IllegalStateException("The instance already exists");
    }

    /**
     * Sets password of the databse
     * @param password the password of the database
     */
    public static void setPassword(String password){
        if(instance==null){
            BBDDManager.password=password;
            return;
        }
        throw new IllegalStateException("The instance already exists");
    }

    /**
     * Sets ip of the database
     * @param ip the ip of the database
     */
    public static void setIp(String ip){
        if(instance==null){
            BBDDManager.ip = ip;
            return;
        }
        throw new IllegalStateException("The instance already exists");
    }

    /**
     * Sets the port of the database
     * @param port the port by which to connect to the database
     */
    public static void setPort(int port){
        if(instance==null){
            BBDDManager.port=port;
            return;
        }
        throw new IllegalStateException("The instance already exists");
    }

    /**
     * Deletes de bbdd singleton in case recreation is needed
     */
    public static void recreateBBDD(){
        if(instance!=null){
            instance.disconnect();
            instance=null;
            return;
        }
        throw new IllegalStateException("The instance doesn't exists");
    }

    /**
     * Private constructor for the database
     * @param bbdd the database to connect
     * @param ip the ip where the database is
     * @param port the port by which to connect
     */
    private BBDDManager(String bbdd, String ip, int port) {
        this.url += ip;
        this.url += ":"+port+"/";
        this.url += bbdd;
    }

    /**
     * Creates a connection to the database
     */
    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Connection");
            connection = (Connection) DriverManager.getConnection(url,user,password);
            if(connection!=null){
                System.out.println("Connection ok");
            }
        } catch (SQLException e) {
            System.out.println(url);
            System.out.println(user);
            System.out.println(BBDDManager.user);
            System.out.println(BBDDManager.password);
            System.err.println("Error connecting to the database "+url+" with user: "+ BBDDManager.user+" and password: "
                    + BBDDManager.password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates query for writting(insert,update,delete) from the database
     * @param query the query to apply
     */
    public void modificationQuery(String query){
        try {
            statement = (Statement)connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Error updating the bbdd contents");
        }
    }

    /**
     * Generates a query for reading information from the bbdd
     * @param query the query to excecute
     * @return the result set of information
     */
    public ResultSet readQuery(String query){
        try {
            statement = (Statement)connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Error reading bbdd");
            return null;
        }

    }

    /**
     * Disconnects from the database
     */
    public void disconnect(){
        try {
            connection.close();
            System.out.println("Disconected");
        } catch (SQLException e) {
            System.err.println("Error closing the bbdd");
        }
    }
}
