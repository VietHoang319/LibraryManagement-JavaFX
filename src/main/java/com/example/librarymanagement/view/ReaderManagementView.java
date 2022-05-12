package com.example.librarymanagement.view;

import com.example.librarymanagement.LibraryManagementApplication;
import com.example.librarymanagement.control.ReaderManagementControl;
import com.example.librarymanagement.ThreadHandle;
import com.example.librarymanagement.datetime.DateTimeFormatter;
import com.example.librarymanagement.model.Reader;
import com.example.librarymanagement.validate.EmailValidate;
import com.example.librarymanagement.validate.PhoneNumberValidate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class ReaderManagementView implements Initializable {
    public static final String READER_IS_NOT_LOCK = "MỞ";
    public static final String READER_IS_LOCK = "KHÓA";
    private static Stage stage = MenuView.stage;
    private static final ReaderManagementControl readerManagementControl = ThreadHandle.getReaderManagementControl();
    EmailValidate emailValidate = new EmailValidate();
    PhoneNumberValidate phoneNumberValidate = new PhoneNumberValidate();
    @FXML
    private TextField tFId;
    @FXML
    private TextField tFName;
    @FXML
    private TextField tFAddress;
    @FXML
    private TextField tFEmail;
    @FXML
    private TextField tFPhoneNumber;
    @FXML
    private TextField tFExpiry;
    @FXML
    private TextField tFLock;
    @FXML
    private TextField tFFind;
    @FXML
    private Button bAdd;
    @FXML
    private Button bDelete;
    @FXML
    private Button bEdit;
    @FXML
    private Button bSave;
    @FXML
    private Button bFind;
    @FXML
    private Button bExtend;
    @FXML
    private TableView<Reader> tVReader;
    @FXML
    private TableColumn<Reader, String> idCol;
    @FXML
    private TableColumn<Reader, String> nameCol;
    @FXML
    private TableColumn<Reader, String> addressCol;
    @FXML
    private TableColumn<Reader, String> emailCol;
    @FXML
    private TableColumn<Reader, String> phoneNumberCol;
    @FXML
    private TableColumn<Reader, LocalDateTime> expiryCol;
    @FXML
    private TableColumn<Reader, Boolean> lockCol;

    private void showDataInTableView(List<Reader> list) {
        ObservableList<Reader> listReader = FXCollections.observableArrayList(list);
        idCol.setCellValueFactory(new PropertyValueFactory<Reader, String>("idReader"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Reader, String>("nameReader"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Reader, String>("addressReader"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Reader, String>("emailReader"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Reader, String>("phoneNumber"));
        expiryCol.setCellValueFactory(new PropertyValueFactory<Reader, LocalDateTime>("expiry"));
        lockCol.setCellValueFactory(new PropertyValueFactory<Reader, Boolean>("lock"));
        tVReader.setItems(listReader);
    }

    private void resetForm() {
        tFName.setDisable(true);
        tFAddress.setDisable(true);
        tFEmail.setDisable(true);
        tFPhoneNumber.setDisable(true);
        bAdd.setDisable(false);
        bEdit.setDisable(true);
        bDelete.setDisable(true);
        bSave.setDisable(true);
        bExtend.setDisable(true);
        showDataInTableView(ReaderManagementControl.getReaders());
    }

    private void resetValue() {
        tFId.setText("");
        tFName.setText("");
        tFAddress.setText("");
        tFEmail.setText("");
        tFPhoneNumber.setText("");
        tFExpiry.setText("");
        tFLock.setText("");
        tFFind.setText("");
    }

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private String autoSetId() {
        LocalDateTime dateTime = LocalDateTime.now();
        return "R" + DateTimeFormatter.formatDateTime(dateTime, DateTimeFormatter.getPatternDatetimeCreateId());
    }

    @FXML
    protected void handleRowSelect() {
        Reader row = tVReader.getSelectionModel().getSelectedItem();
        if (row != null) {
            tFId.setText(String.valueOf(row.getIdReader()));
            tFName.setText(row.getNameReader());
            tFAddress.setText(row.getAddressReader());
            tFExpiry.setText(String.valueOf(row.getExpiry()));
            tFExpiry.setText(DateTimeFormatter.formatDateTime(row.getExpiry(), DateTimeFormatter.getPatternDatetime()));
            if (row.isLock()) {
                tFLock.setText(READER_IS_LOCK);
            } else {
                tFLock.setText(READER_IS_NOT_LOCK);
            }

            tFEmail.setText(row.getEmailReader());
            tFPhoneNumber.setText(row.getPhoneNumber());
            tFName.setDisable(false);
            tFAddress.setDisable(false);
            tFEmail.setDisable(false);
            tFPhoneNumber.setDisable(false);

            bAdd.setDisable(false);
            bSave.setDisable(true);
            bEdit.setDisable(false);
            bDelete.setDisable(false);
            bExtend.setDisable(false);
        }
    }

    @FXML
    protected void onAddButtonReaderClick() {
        LocalDateTime dateTime = LocalDateTime.now();
        tFName.setDisable(false);
        tFAddress.setDisable(false);
        tFEmail.setDisable(false);
        tFPhoneNumber.setDisable(false);
        bAdd.setDisable(true);
        bSave.setDisable(false);
        bEdit.setDisable(true);
        bDelete.setDisable(true);
        bExtend.setDisable(true);
        resetValue();
        tFId.setText(autoSetId());
        tFExpiry.setText(DateTimeFormatter.formatDateTime(dateTime.plusMinutes(5), DateTimeFormatter.getPatternDatetime()));
        tFLock.setText(READER_IS_NOT_LOCK);
    }

    @FXML
    protected void onSaveButtonReaderClick() {
        if (!tFId.getText().equals("")) {
            if (tFName.getText().equals("")) {
                showAlert("Bạn phải nhập tên nhân viên");
            } else if (!tFEmail.getText().equals("") && !emailValidate.validate(tFEmail.getText())) {
                showAlert("Email của bạn không đúng định dạng");
            } else if (!tFPhoneNumber.getText().equals("") && !phoneNumberValidate.validate(tFPhoneNumber.getText())) {
                showAlert("Số điện thoại của bạn không đúng định dạng");
            } else {
                readerManagementControl.add(new Reader(tFId.getText(), tFName.getText(), tFAddress.getText(), tFEmail.getText(),
                        tFPhoneNumber.getText(), DateTimeFormatter.parseDatetime(tFExpiry.getText(), DateTimeFormatter.getPatternDatetime()), false));
                resetValue();
                resetForm();
            }
        }
    }

    @FXML
    protected void onEditButtonReaderClick() {
        if (!tFId.getText().equals("")) {
            if (tFName.getText().equals("")) {
                showAlert("Bạn phải nhập tên nhân viên");
            } else if (!tFEmail.getText().equals("") && !emailValidate.validate(tFEmail.getText())) {
                showAlert("Email của bạn không đúng định dạng");
            } else if (!tFPhoneNumber.getText().equals("") && !phoneNumberValidate.validate(tFPhoneNumber.getText())) {
                showAlert("Số điện thoại của bạn không đúng định dạng");
            } else {
                boolean check;
                if (tFLock.getText().equals(READER_IS_LOCK)) {
                    check = true;
                } else {
                    check = false;
                }
                readerManagementControl.update(tFId.getText(), new Reader(tFId.getText(), tFName.getText(), tFAddress.getText(), tFEmail.getText(),
                        tFPhoneNumber.getText(), DateTimeFormatter.parseDatetime(tFExpiry.getText(), DateTimeFormatter.getPatternDatetime()), check));
                resetValue();
                resetForm();
            }
        }
    }

    @FXML
    protected void onDeleteButtonReaderClick() {
        if (!tFId.getText().equals("")) {
            readerManagementControl.delete(tFId.getText());
            resetValue();
            resetForm();
        }
    }

    @FXML
    protected void onFindButtonStaffClick() {
        if (tFFind.getText().equals("")) {
            showDataInTableView(ReaderManagementControl.getReaders());
        } else {
            showDataInTableView(readerManagementControl.findReaderByIdOrName(tFFind.getText().toUpperCase().trim()));
        }
    }

    @FXML
    protected void onExtendButtonReaderClick() {
        LocalDateTime localDateTime = LocalDateTime.now();
        readerManagementControl.extendExpiry(tFId.getText(), new Reader(tFId.getText(), tFName.getText(), tFAddress.getText(), tFEmail.getText(),
                tFPhoneNumber.getText(), localDateTime.plusMinutes(5), false));
        resetValue();
        resetForm();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetForm();
        resetValue();
    }

    public static void exit() {
        try {
            stage.close();
            stage = new Stage();
            MenuView.setStage(stage);
            FXMLLoader fxmlLoader = new FXMLLoader(LibraryManagementApplication.class.getResource("menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1500, 800);
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.setX(10);
            stage.setY(15);
            stage.setOnCloseRequest(e -> MenuView.exit());
            stage.show();
        } catch (IOException e) {
            System.out.println("Không tìm thấy màn");
        }
    }
}
