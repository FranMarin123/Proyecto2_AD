package example.tests;

import example.model.Connection.ConnectionProperties;
import example.utils.XMLManager;

public class ConnectionSave {
    public static void main(String[] args) {
        ConnectionProperties c = new ConnectionProperties("localhost","3306","","root","");
        XMLManager.writeXML(c,"connection.xml");
    }
}
