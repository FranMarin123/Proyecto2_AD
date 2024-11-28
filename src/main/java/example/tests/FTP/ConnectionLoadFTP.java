package example.tests.FTP;

import example.model.connection.FTP.ConnectionFTP;
import example.model.connection.FTP.ConnectionPropertiesFTP;
import example.utils.XMLManager;

import java.io.IOException;

public class ConnectionLoadFTP {
    public static void main(String[] args) throws IOException {
        ConnectionPropertiesFTP c = XMLManager.readXML(new ConnectionPropertiesFTP(),"connectionFTP.xml");
        ConnectionFTP.getConnection();
        System.out.println(ConnectionFTP.getConnection());
        System.out.println("Descargando");
        //System.out.println(ConnectionFTP.getConnection().downloadFile("/ftp/prueba.txt","prueba.txt"));
        System.out.println(ConnectionFTP.getConnection().uploadFile("C:/Users/franc/Desktop/PalomaJump.mp4","/ftp/PalomaJump.mp4"));
    }
}
