package com.example.librarymanagement.view;

import com.example.librarymanagement.datetime.DateTimeFormatter;
import com.example.librarymanagement.control.BookManagenmentControl;
import com.example.librarymanagement.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        tFFind.setText("");
    }

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }

    private String autoSetId() {
        LocalDateTime dateTime = LocalDateTime.now();
        return "B" + DateTimeFormatter.formatDateTime(dateTime);
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
        tFId.setText(autoSetId());
    }

    @FXML
    public void onSaveButtonStaffClick() {
        if (tFName.getText().equals("")) {
            showAlert("Bạn phải nhập tên sách");
        } else if (tFNumberOfBook.getText().equals("")) {
            showAlert("Bạn phải nhập số lượng sách");
        } else {
            try {
                int reprintTimes;
                Year publishingYear;
                if (tFReprintTimes.getText().equals(NULLVALUE)) {
                    reprintTimes = 0;
                } else {
                    reprintTimes = Integer.parseInt(tFReprintTimes.getText().trim());
                }
                if (tFPublishingYear.getText().equals(NULLVALUE)) {
                    publishingYear = null;
                } else {
                    publishingYear = Year.parse(tFPublishingYear.getText().trim());
                }
                bookManagenmentControl.add(new Book(tFId.getText().trim(), tFName.getText().toUpperCase().trim(), tFAuthor.getText().toUpperCase().trim(), tFCategory.getText().trim(),
                        tFPublishingCompany.getText().toLowerCase().trim(), publishingYear, reprintTimes, Integer.parseInt(tFNumberOfBook.getText().trim())));
                resetValue();
                resetForm();
            } catch (NumberFormatException e) {
                showAlert("Bạn phải nhập số cho lần xuất bản và số lượng");
            }

        }
    }

    @FXML
    public void onEditButtonStaffClick() {
        if(!tFId.getText().equals("")) {
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
                    reprintTimes = Integer.parseInt(tFReprintTimes.getText().trim());
                }
                if (tFPublishingYear.getText().equals(NULLVALUE)) {
                    publishingYear = null;
                } else {
                    publishingYear = Year.parse(tFPublishingYear.getText().trim());
                }
                bookManagenmentControl.update(tFId.getText(), new Book(tFId.getText().trim(), tFName.getText().toUpperCase().trim(), tFAuthor.getText().toUpperCase().trim(), tFCategory.getText().trim(),
                        tFPublishingCompany.getText().toLowerCase().trim(), publishingYear, reprintTimes, Integer.parseInt(tFNumberOfBook.getText().trim())));
                resetValue();
                resetForm();
            }
        }
    }

    @FXML
    public void onDeleteButtonStaffClick() {
        if (!tFId.getText().equals("")) {
            bookManagenmentControl.delete(tFId.getText());
            resetValue();
            resetForm();
        }
    }

    @FXML
    public void handleRowSelect() {
        Book row = tVBook.getSelectionModel().getSelectedItem();
        if (row != null) {
            tFId.setText(row.getIdBook());
            tFName.setText(row.getNameBook());
            tFAuthor.setText(row.getAuthor());
            tFCategory.setText(row.getCategory());
            tFPublishingCompany.setText(row.getPublishingCompany());
            String year;
            if (String.valueOf(row.getPublishingYear()).equals("null")) {
                year = "";
            } else {
                year = String.valueOf(row.getPublishingYear());
            }
            tFPublishingYear.setText(year);
            tFReprintTimes.setText(String.valueOf(row.getReprintTimes()));
            tFNumberOfBook.setText(String.valueOf(row.getNumberOfBook()));
            tFName.setDisable(false);
            tFAuthor.setDisable(false);
            tFCategory.setDisable(false);
            tFPublishingCompany.setDisable(false);
            tFPublishingYear.setDisable(false);
            tFReprintTimes.setDisable(false);
            tFNumberOfBook.setDisable(false);

            bAdd.setDisable(false);
            bSave.setDisable(true);
            bEdit.setDisable(false);
            bDelete.setDisable(false);
        }
    }

    @FXML
    public void onFindButtonStaffClick() {
        if (tFFind.getText().equals("")) {
            showDataInTableView(BookManagenmentControl.getListStaff());
        } else {
            showDataInTableView(bookManagenmentControl.findBookByIdOrNameOrCompanyOrAuthor(tFFind.getText().toLowerCase().trim()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetForm();
        resetValue();
    }
}
