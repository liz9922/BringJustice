module com.example.bringjustice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires okhttp3;
    requires org.json;
    requires com.fasterxml.jackson.databind;


    opens com.example.bringjustice to javafx.fxml;
    exports com.example.bringjustice;
}