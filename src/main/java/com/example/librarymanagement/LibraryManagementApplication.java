package com.example.librarymanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LibraryManagementApplication extends Application {
    public static Stage stageSender;

    @Override
    public void start(Stage stage) throws IOException {
        stageSender = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryManagementApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 350);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}