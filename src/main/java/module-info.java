module example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;
    requires org.apache.commons.net;
    requires javafx.media;
    requires javafx.base;

    opens example to javafx.fxml;
    opens example.model.connection.FTP to java.xml.bind;
    opens example.model.connection.database to java.xml.bind;
    exports example;
}
