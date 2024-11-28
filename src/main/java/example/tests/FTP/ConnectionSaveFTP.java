package example.tests.FTP;

import example.model.connection.FTP.ConnectionPropertiesFTP;
import example.utils.XMLManager;

public class ConnectionSaveFTP {
    public static void main(String[] args) {
        ConnectionPropertiesFTP c = new ConnectionPropertiesFTP("localhost","22","user_ftp","ftp");
        XMLManager.writeXML(c,"connectionFTP.xml");
    }
}
