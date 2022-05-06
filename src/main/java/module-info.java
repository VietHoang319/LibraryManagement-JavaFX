module com.example.librarymanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.librarymanagement to javafx.fxml;
    exports com.example.librarymanagement;
    exports com.example.librarymanagement.view;
    opens com.example.librarymanagement.view to javafx.fxml;
    opens com.example.librarymanagement.model;
}