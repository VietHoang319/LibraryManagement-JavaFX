package com.example.librarymanagement.view;

import com.example.librarymanagement.control.StaffManagenment;
import com.example.librarymanagement.model.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StaffManagementView implements Initializable {
    private final StaffManagenment staffManagenment = new StaffManagenment();
    private final ObservableList<String> roles = FXCollections.observableArrayList("ADMIN", "NHÂN VIÊN");

    @FXML
    private TextField tFId;
    @FXML
    private TextField tFName;
    @FXML
    private TextField tFUsername;
    @FXML
    private PasswordField tFPassword;
    @FXML
    private TextField tFAddress;
    @FXML
    private TextField tFEmail;
    @FXML
    private TextField tFPhoneNumber;
    @FXML
    private ComboBox<String> cBRole;
    @FXML
    private Button bAdd;
    @FXML
    private Button bEdit;
    @FXML
    private Button bDelete;
    @FXML
    private Button bSave;
    @FXML
    private Button bFind;
    @FXML
    private TextField tFFind;
    @FXML
    private TableView<Staff> tVStaff;
    @FXML
    private TableColumn<Staff, Integer> idCol;
    @FXML
    private TableColumn<Staff, String> nameCol;
    @FXML
    private TableColumn<Staff, String> addressCol;
    @FXML
    private TableColumn<Staff, String> emailCol;
    @FXML
    private TableColumn<Staff, String> phoneNumberCol;
    @FXML
    private TableColumn<Staff, String> roleCol;

    private void showDataInTableView(List<Staff> list) {
        ObservableList<Staff> listStaff = FXCollections.observableArrayList(list);
        idCol.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("idStaff"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("nameStaff"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("addressStaff"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("emailStaff"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("phoneNumberStaff"));
        roleCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("role"));
        tVStaff.setItems(listStaff);
    }

    private void resetForm() {
        tFId.setDisable(true);
        tFName.setDisable(true);
        tFUsername.setDisable(true);
        tFPassword.setDisable(true);
        tFAddress.setDisable(true);
        tFEmail.setDisable(true);
        tFPhoneNumber.setDisable(true);
        cBRole.setDisable(true);
        bAdd.setDisable(false);
        bEdit.setDisable(true);
        bDelete.setDisable(true);
        bSave.setDisable(true);
        cBRole.setItems(roles);
        showDataInTableView(StaffManagenment.getListStaff());
    }

    private void resetValue() {
        tFId.setText("");
        tFName.setText("");
        tFUsername.setText("");
        tFPassword.setText("");
        tFAddress.setText("");
        tFEmail.setText("");
        tFPhoneNumber.setText("");
        cBRole.setValue("");
    }

    private void showAlertWithoutHeaderText(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }

    @FXML
    protected void onAddButtonStaffClick() {
        tFName.setDisable(false);
        tFUsername.setDisable(false);
        tFPassword.setDisable(false);
        tFAddress.setDisable(false);
        tFEmail.setDisable(false);
        tFPhoneNumber.setDisable(false);
        cBRole.setDisable(false);
        bAdd.setDisable(true);
        bSave.setDisable(false);
        resetValue();
        tFId.setText(String.valueOf(StaffManagenment.getListStaff().get(StaffManagenment.getListStaff().size() - 1).getIdStaff() + 1));
    }

    @FXML
    protected void onSaveButtonStaffClick() {
        if (tFName.getText().equals("")) {
            showAlertWithoutHeaderText("Bạn phải nhập tên nhân viên");
        } else if (tFUsername.getText().equals("")) {
            showAlertWithoutHeaderText("Bạn phải nhập tên đăng nhập");
        } else if (tFPassword.getText().equals("")) {
            showAlertWithoutHeaderText("Bạn phải nhập mật khẩu");
        } else {
            staffManagenment.add(new Staff(tFName.getText(), tFAddress.getText(), tFEmail.getText(), tFPhoneNumber.getText(), tFUsername.getText(), tFPassword.getText(), cBRole.getValue()));
            resetValue();
            resetForm();
        }
    }

    @FXML
    public void onDeleteButtonStaffClick() {
        if (!tFId.getText().equals("")){
            staffManagenment.delete(tFId.getId());
            resetValue();
            resetForm();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetValue();
        resetForm();
    }
}
