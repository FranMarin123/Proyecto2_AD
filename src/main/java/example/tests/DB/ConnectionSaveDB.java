package example.tests.DB;

import example.model.connection.database.ConnectionPropertiesDB;
import example.utils.XMLManager;

public class ConnectionSaveDB {
    public static void main(String[] args) {
        ConnectionPropertiesDB c = new ConnectionPropertiesDB("localhost","3306","proyecto2ad","root","");
        XMLManager.writeXML(c,"connection.xml");
    }
}
