module healthCareSystem {
    requires javafx.controls;
    requires javafx.fxml;

    opens controllers to javafx.fxml;
    exports controllers to javafx.fxml;

    opens application to javafx.fxml;
    exports application;
    
    opens models to javafx.fxml;
    exports models;
}