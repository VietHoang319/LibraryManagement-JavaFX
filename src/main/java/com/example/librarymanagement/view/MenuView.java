package com.example.librarymanagement.view;

import com.example.librarymanagement.LibraryManagementApplication;
import com.example.librarymanagement.control.StaffManagementControl;
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

public class MenuView implements Initializable {
    protected static StaffManagementControl staffManagementControl;
    private final Stage stage = LoginView.stage;
    @FXML
    private Label lName;
    @FXML
    private Label lRole;
    @FXML
    private Button bInfor;
    @FXML
    private Button bBook;
    @FXML
    private Button bCallCard;
    @FXML
    private Button bReturnCard;
    @FXML
    private Button bStaff;
    @FXML
    private Button bReader;

    public void setStaffManagementControl(StaffManagementControl staffManagementControl) {
        this.staffManagementControl = staffManagementControl;
    }

    @FXML
    protected void onCallCardButtonClick() {

    }

    @FXML
    protected void onReturnCallButtonClick() {

    }

    @FXML
    protected void onReaderButtonClick() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryManagementApplication.class.getResource("reader-management.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 800);
        stage.setTitle("Quản lý độc giả");
        stage.setScene(scene);
        stage.setX(10);
        stage.setY(15);
        stage.show();
    }

    @FXML
    protected void onInforButtonClick() {

    }

    @FXML
    protected void onBookButtonClick() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryManagementApplication.class.getResource("book-management.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 800);
        stage.setTitle("Quản lý sách");
        stage.setScene(scene);
        stage.setX(10);
        stage.setY(15);
        stage.show();
    }

    @FXML
    protected void onStaffButtonClick() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryManagementApplication.class.getResource("staff-management.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 800);
        stage.setTitle("Quản lý nhân viên");
        stage.setScene(scene);
        stage.setX(10);
        stage.setY(15);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lName.setText(lName.getText() + staffManagementControl.getCurrentStaff().getNameStaff());
        lRole.setText(lRole.getText() + staffManagementControl.getCurrentStaff().getRole().toLowerCase());
        if (staffManagementControl.getCurrentStaff().getRole().equals("NHÂN VIÊN")) {
            bBook.setVisible(false);
            bStaff.setVisible(false);
        }
        if (staffManagementControl.getCurrentStaff().getRole().equals("THỦ KHO")) {
            bStaff.setVisible(false);
        }
    }
}
