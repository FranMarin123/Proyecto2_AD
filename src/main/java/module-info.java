module example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;

    opens example to javafx.fxml;
    exports example;
}
