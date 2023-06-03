module com.example.bringjustice {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.bringjustice to javafx.fxml;
    exports com.example.bringjustice;
}