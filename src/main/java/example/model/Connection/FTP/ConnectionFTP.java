package example.model.Connection.FTP;

import example.utils.XMLManager;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionFTP {
    private final static String FILE = "connectionFTP.xml";
    private static ConnectionFTP _instance;
    private static FTPClient ftpClient;

    private ConnectionFTP() {
        ConnectionPropertiesFTP properties = (ConnectionPropertiesFTP) XMLManager.readXML(new ConnectionPropertiesFTP(), FILE);

        try {
            ftpClient = new FTPClient();
            ftpClient.connect(properties.getServer(), Integer.valueOf(properties.getPort()));
            ftpClient.login(properties.getUser(), properties.getPassword());
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

        } catch (IOException e) {
            e.printStackTrace();
            ftpClient = null;
        }
    }

    public static ConnectionFTP getConnection() {
        if (_instance == null) {
            _instance = new ConnectionFTP();
        }
        return _instance;
    }

    public static FTPClient getFtpClient() {
        if (_instance == null) {
            _instance = new ConnectionFTP();
        }
        return ftpClient;
    }

    public boolean downloadFile(String remoteFilePath, String localFilePath) {
        File localFile = new File(localFilePath);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(localFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (ftpClient.retrieveFile(remoteFilePath, outputStream)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean uploadFile(String localFilePath, String remoteFilePath) {
        File localFile = new File(localFilePath);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(localFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (ftpClient.storeFile(remoteFilePath, inputStream)) {
                return true;
            }
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<File> listFiles() {
        FTPFile[] filesFromFTP = new FTPFile[0];
        List<File> filesToReturn = new ArrayList<>();
        try {
            filesFromFTP = ftpClient.listFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (FTPFile file : filesFromFTP) {
            filesToReturn.add(new File(file.getLink()));
        }
        return filesToReturn;
    }

    private static boolean renameFile(String oldName, String newName) {
        boolean success = false;
        try {
            success = ftpClient.rename(oldName, newName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (success) {
            return true;
        }
        return false;
    }

    public static boolean deleteFile(String filePath) {
        boolean success = false;
        try {
            success = ftpClient.deleteFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (success) {
            return true;
        }
        return false;
    }

    public static boolean close() {
        try {
            // Disconnect from the FTP server
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
