module com.example.edbms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.edbms to javafx.fxml;
    exports com.example.edbms;
}