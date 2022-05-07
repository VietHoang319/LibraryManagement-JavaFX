package com.example.librarymanagement.view;

import com.example.librarymanagement.control.StaffManagenmentControl;
import com.example.librarymanagement.model.Staff;
import com.example.librarymanagement.validate.EmailValidate;
import com.example.librarymanagement.validate.PhoneNumberValidate;
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
    private final StaffManagenmentControl staffManagenmentControl = new StaffManagenmentControl();

    private final EmailValidate emailValidate = new EmailValidate();
    private final PhoneNumberValidate phoneNumberValidate = new PhoneNumberValidate();
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
        showDataInTableView(StaffManagenmentControl.getListStaff());
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

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }

    @FXML
    protected void handleRowSelect() {
        Staff row = tVStaff.getSelectionModel().getSelectedItem();
        if (row != null) {
            tFId.setText(String.valueOf(row.getIdStaff()));
            tFName.setText(row.getNameStaff());
            tFAddress.setText(row.getAddressStaff());
            tFPassword.setText(row.getPassword());
            tFUsername.setText(row.getUsername());
            tFEmail.setText(row.getEmailStaff());
            tFPhoneNumber.setText(row.getPhoneNumberStaff());
            cBRole.setValue(row.getRole());
            tFName.setDisable(false);
            tFUsername.setDisable(false);
            tFPassword.setDisable(false);
            tFAddress.setDisable(false);
            tFEmail.setDisable(false);
            tFPhoneNumber.setDisable(false);
            cBRole.setDisable(false);

            bAdd.setDisable(false);
            bSave.setDisable(true);
            bEdit.setDisable(false);
            bDelete.setDisable(false);
        }
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
        bEdit.setDisable(true);
        bDelete.setDisable(true);
        resetValue();
        int sizeOfListEqualZero = 0;
        if (StaffManagenmentControl.getListStaff().size() == sizeOfListEqualZero) {
            tFId.setText("0");
        } else {
            tFId.setText(String.valueOf(StaffManagenmentControl.getListStaff().get(StaffManagenmentControl.getListStaff().size() - 1).getIdStaff() + 1));
        }
    }

    @FXML
    protected void onSaveButtonStaffClick() {
        if (tFName.getText().equals("")) {
            showAlert("Bạn phải nhập tên nhân viên");
        } else if (tFUsername.getText().equals("")) {
            showAlert("Bạn phải nhập tên đăng nhập");
        } else if (tFPassword.getText().equals("")) {
            showAlert("Bạn phải nhập mật khẩu");
        } else if (cBRole.getValue().equals("")) {
            showAlert("Bạn phải chọn chức vụ cho nhân viên");
        } else if (!tFEmail.getText().equals("") && !emailValidate.validate(tFEmail.getText())) {
            showAlert("Email của bạn không đúng định dạng");
        } else if (!tFPhoneNumber.getText().equals("") && !phoneNumberValidate.validate(tFPhoneNumber.getText())) {
            showAlert("Số điện thoại của bạn không đúng định dạng");
        } else {
            staffManagenmentControl.add(new Staff(tFName.getText().trim(), tFAddress.getText().trim(), tFEmail.getText().trim(), tFPhoneNumber.getText().trim(), tFUsername.getText().trim(), tFPassword.getText().trim(), cBRole.getValue().trim()));
            resetValue();
            resetForm();
        }
    }

    @FXML
    protected void onDeleteButtonStaffClick() {
        if (!tFId.getText().equals("")) {
            staffManagenmentControl.delete(tFId.getText());
            resetValue();
            resetForm();
        }
    }

    @FXML
    protected void onEditButtonStaffClick() {
        if (!tFId.getText().equals("")) {
            if (tFName.getText().equals("")) {
                showAlert("Bạn phải nhập tên nhân viên");
            } else if (tFUsername.getText().equals("")) {
                showAlert("Bạn phải nhập tên đăng nhập");
            } else if (tFPassword.getText().equals("")) {
                showAlert("Bạn phải nhập mật khẩu");
            } else if (cBRole.getValue().equals("")) {
                showAlert("Bạn phải chọn chức vụ cho nhân viên");
            } else if (!tFEmail.getText().equals("") && !emailValidate.validate(tFEmail.getText())) {
                showAlert("Email của bạn không đúng định dạng");
            } else if (!tFPhoneNumber.getText().equals("") && !phoneNumberValidate.validate(tFPhoneNumber.getText())) {
                showAlert("Số điện thoại của bạn không đúng định dạng");
            } else {
                staffManagenmentControl.update(tFId.getText(), new Staff(Integer.parseInt(tFId.getText()), tFName.getText(), tFAddress.getText(),
                        tFEmail.getText(), tFPhoneNumber.getText(), tFUsername.getText(), tFPassword.getText(), cBRole.getValue()));
                resetValue();
                resetForm();
            }
        }
    }

    @FXML
    protected void onFindButtonStaffClick() {
        if (tFFind.getText().equals("")) {
            showDataInTableView(StaffManagenmentControl.getListStaff());
        } else {
            showDataInTableView(staffManagenmentControl.findStaffByIdOrName(tFFind.getText()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetValue();
        resetForm();
    }
}
