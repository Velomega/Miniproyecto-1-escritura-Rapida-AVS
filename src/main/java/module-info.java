module com.example.typinggame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.typinggame to javafx.fxml;
    opens com.example.typinggame.controller to javafx.fxml;
    exports com.example.typinggame;
    opens com.example.typinggame.model.Interface to javafx.fxml;
}