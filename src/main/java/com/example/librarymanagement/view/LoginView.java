package com.example.librarymanagement.view;

import com.example.librarymanagement.LibraryManagementApplication;
import com.example.librarymanagement.control.StaffManagementControl;
import com.example.librarymanagement.ThreadHandle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginView implements Initializable {
    protected static Stage stage;
    protected static StaffManagementControl staffManagementControl = ThreadHandle.getStaffManagementControl();
    @FXML
    private TextField tFUsername;
    @FXML
    private TextField tFPassword;
    @FXML
    private Label lStatus;
    @FXML
    private Button bLogin;

    public static void setStage(Stage stage1) {
        stage = stage1;
    }

    private void ResetValue() {
        lStatus.setText("");
        tFPassword.setText("");
        tFUsername.setText("");
    }

    @FXML
    protected void onLoginButtonClick() throws Exception {
        if (staffManagementControl.Login(tFUsername.getText(), tFPassword.getText())) {
            MenuView.setStage(stage);
            ResetValue();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(LibraryManagementApplication.class.getResource("menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1500, 800);
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.setX(10);
            stage.setY(15);
            stage.setOnCloseRequest(e -> MenuView.exit());
            stage.show();
        } else {
            lStatus.setText("SAI TÊN ĐĂNG NHẬP HOẶC MẬT KHẨU");
            tFPassword.setText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResetValue();
    }
}
