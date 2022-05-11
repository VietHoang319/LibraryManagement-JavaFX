package com.example.librarymanagement.view;

import com.example.librarymanagement.LibraryManagementApplication;
import com.example.librarymanagement.control.ReaderManagementControl;
import com.example.librarymanagement.control.StaffManagementControl;
import com.example.librarymanagement.ThreadHandle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuView implements Initializable {
    protected static StaffManagementControl staffManagementControl = ThreadHandle.getStaffManagementControl();
    protected static Stage stage;
    @FXML
    private Label lName;
    @FXML
    private Label lRole;
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

    public static void setStage(Stage stage1) {
        stage = stage1;
    }

    @FXML
    protected void onCallCardButtonClick() throws Exception{
        ReaderManagementControl.autoSetLock();
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryManagementApplication.class.getResource("call-card-management.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 800);
        stage.setTitle("Quản lý phiếu mượn");
        stage.setScene(scene);
        stage.setX(10);
        stage.setY(15);
        stage.setOnCloseRequest(e -> CallCardManagementView.exit());
        stage.show();
    }

    @FXML
    protected void onReturnCallButtonClick() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryManagementApplication.class.getResource("return-card-management.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 800);
        stage.setTitle("Quản lý phiếu trả");
        stage.setScene(scene);
        stage.setX(10);
        stage.setY(15);
        stage.setOnCloseRequest(e -> ReturnCardManagementView.exit());
        stage.show();
    }

    @FXML
    protected void onReaderButtonClick() throws Exception{
        ReaderManagementControl.autoSetLock();
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryManagementApplication.class.getResource("reader-management.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 800);
        stage.setTitle("Quản lý độc giả");
        stage.setScene(scene);
        stage.setX(10);
        stage.setY(15);
        stage.setOnCloseRequest(e -> ReaderManagementView.exit());
        stage.show();
    }

    @FXML
    protected void onBookButtonClick() throws Exception{
//        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryManagementApplication.class.getResource("book-management.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 800);
        stage.setTitle("Quản lý sách");
        stage.setScene(scene);
        stage.setX(10);
        stage.setY(15);
        stage.setOnCloseRequest(e -> BookManagementView.exit());
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
        stage.setOnCloseRequest(e -> StaffManagementView.exit());
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

    public static void exit() {
        try {
            stage.close();
            stage = new Stage();
            LoginView.setStage(stage);
            LibraryManagementApplication.init(stage);
        } catch (IOException e) {
            System.out.println("Không tìm thấy scene");
        }
    }
}
