package example.model.connection.database;

import example.utils.XMLManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {
    private final static String FILE="connection.xml";
    private static ConnectionMySQL _instance;
    private static Connection conn;

    private ConnectionMySQL(){
        ConnectionPropertiesDB properties = (ConnectionPropertiesDB) XMLManager.readXML(new ConnectionPropertiesDB(),FILE);

        try {
            conn = DriverManager.getConnection(properties.getURL(),properties.getUser(),properties.getPassword());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        if(_instance==null){
            _instance = new ConnectionMySQL();
        }
        return conn;
    }

    public static void closeConnection(){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
