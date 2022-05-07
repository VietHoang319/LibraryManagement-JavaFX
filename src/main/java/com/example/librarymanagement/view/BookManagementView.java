package com.example.librarymanagement.view;

import com.example.librarymanagement.control.BookManagenmentControl;
import com.example.librarymanagement.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.Year;
import java.util.List;
import java.util.ResourceBundle;

public class BookManagementView implements Initializable {
    public static final String NULLVALUE = "";
    BookManagenmentControl bookManagenmentControl = new BookManagenmentControl();
    @FXML
    private TextField tFId;
    @FXML
    private TextField tFName;
    @FXML
    private TextField tFAuthor;
    @FXML
    private TextField tFCategory;
    @FXML
    private TextField tFPublishingCompany;
    @FXML
    private TextField tFPublishingYear;
    @FXML
    private TextField tFReprintTimes;
    @FXML
    private TextField tFNumberOfBook;
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
    private TableView<Book> tVBook;
    @FXML
    private TableColumn<Book, String> idCol;
    @FXML
    private TableColumn<Book, String> nameCol;
    @FXML
    private TableColumn<Book, String> authorCol;
    @FXML
    private TableColumn<Book, String> categoryCol;
    @FXML
    private TableColumn<Book, String> publishingCompanyCol;
    @FXML
    private TableColumn<Book, Year> publishingYearCol;
    @FXML
    private TableColumn<Book, Integer> reprintTimesCol;
    @FXML
    private TableColumn<Book, Integer> numberOfBookCol;

    private void showDataInTableView(List<Book> list) {
        ObservableList<Book> listBook = FXCollections.observableArrayList(list);
        idCol.setCellValueFactory(new PropertyValueFactory<Book, String>("idBook"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Book, String>("nameBook"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
        publishingCompanyCol.setCellValueFactory(new PropertyValueFactory<Book, String>("publishingCompany"));
        publishingYearCol.setCellValueFactory(new PropertyValueFactory<Book, Year>("publishingYear"));
        reprintTimesCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("reprintTimes"));
        numberOfBookCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("numberOfBook"));
        tVBook.setItems(listBook);
    }

    private void resetForm() {
        tFId.setDisable(true);
        tFName.setDisable(true);
        tFAuthor.setDisable(true);
        tFCategory.setDisable(true);
        tFPublishingCompany.setDisable(true);
        tFPublishingYear.setDisable(true);
        tFReprintTimes.setDisable(true);
        tFNumberOfBook.setDisable(true);
        bAdd.setDisable(false);
        bEdit.setDisable(true);
        bDelete.setDisable(true);
        bSave.setDisable(true);
        showDataInTableView(BookManagenmentControl.getListStaff());
    }

    private void resetValue() {
        tFId.setText("");
        tFName.setText("");
        tFAuthor.setText("");
        tFCategory.setText("");
        tFPublishingCompany.setText("");
        tFPublishingYear.setText("");
        tFReprintTimes.setText("");
        tFNumberOfBook.setText("");
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
    public void onAddButtonStaffClick() {
        tFName.setDisable(false);
        tFAuthor.setDisable(false);
        tFCategory.setDisable(false);
        tFPublishingCompany.setDisable(false);
        tFPublishingYear.setDisable(false);
        tFReprintTimes.setDisable(false);
        tFNumberOfBook.setDisable(false);
        bAdd.setDisable(true);
        bSave.setDisable(false);
        bEdit.setDisable(true);
        bDelete.setDisable(true);
        resetValue();
        tFId.setText("");
    }

    @FXML
    public void onSaveButtonStaffClick() {
        if (tFName.getText().equals("")) {
            showAlert("Bạn phải nhập tên sách");
        } else if (tFNumberOfBook.getText().equals("")) {
            showAlert("Bạn phải nhập số lượng sách");
        } else {
            int reprintTimes;
            Year publishingYear;
            if (tFReprintTimes.getText().equals(NULLVALUE)) {
                reprintTimes = 0;
            } else {
                reprintTimes = Integer.parseInt(tFReprintTimes.getText());
            }
            if (tFPublishingYear.getText().equals(NULLVALUE)) {
                publishingYear = null;
            } else {
                publishingYear = Year.parse(tFPublishingYear.getText());
            }
            bookManagenmentControl.add(new Book(tFId.getText(), tFName.getText(), tFAuthor.getText(), tFCategory.getText(),
                    tFPublishingCompany.getText(), publishingYear, reprintTimes, Integer.parseInt(tFNumberOfBook.getText())));
            resetValue();
            resetForm();
        }
    }

    @FXML
    public void onEditButtonStaffClick() {

    }

    @FXML
    public void onDeleteButtonStaffClick() {

    }

    @FXML
    public void handleRowSelect() {

    }

    @FXML
    public void onFindButtonStaffClick() {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetForm();
        resetValue();
    }
}
