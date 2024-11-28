package example.tests.DB;

import example.model.connection.database.ConnectionPropertiesDB;
import example.utils.XMLManager;

public class ConnectionLoadDB {
    public static void main(String[] args) {
        ConnectionPropertiesDB c = XMLManager.readXML(new ConnectionPropertiesDB(),"connection.xml");
        System.out.println(c);
    }
}
