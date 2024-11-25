module example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;
    requires org.apache.commons.net;

    opens example to javafx.fxml;
    opens example.model.Connection.FTP to java.xml.bind;
    exports example;
}
