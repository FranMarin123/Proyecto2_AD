package example.tests.DB;

import example.model.Connection.Database.ConnectionPropertiesDB;
import example.utils.XMLManager;

public class ConnectionSaveDB {
    public static void main(String[] args) {
        ConnectionPropertiesDB c = new ConnectionPropertiesDB("localhost","3306","","root","");
        XMLManager.writeXML(c,"connection.xml");
    }
}
