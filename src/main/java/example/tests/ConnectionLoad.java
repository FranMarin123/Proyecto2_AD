package example.tests;

import example.model.Connection.ConnectionProperties;
import example.utils.XMLManager;

public class ConnectionLoad {
    public static void main(String[] args) {
        ConnectionProperties c = XMLManager.readXML(new ConnectionProperties(),"connection.xml");
        System.out.println(c);
    }
}
