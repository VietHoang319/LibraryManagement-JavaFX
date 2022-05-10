package com.example.librarymanagement.view;

import com.example.librarymanagement.FxUtilTest;
import com.example.librarymanagement.control.BookManagementControl;
import com.example.librarymanagement.control.ReaderManagementControl;
import com.example.librarymanagement.control.StaffManagementControl;
import com.example.librarymanagement.datetime.DateTimeFormatter;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Reader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CallCardManagementView implements Initializable {
    private final ReaderManagementControl readerManagementControl = new ReaderManagementControl();
    private final BookManagementControl bookManagementControl = new BookManagementControl();
    private final StaffManagementControl staffManagementControl = MenuView.staffManagementControl;
    private ObservableList<String> readers = FXCollections.observableArrayList(readerManagementControl.getListId());
    private ObservableList<String> books = FXCollections.observableArrayList(bookManagementControl.getListId());
    @FXML
    private TextField tFNameBook;
    @FXML
    private TextField tFPublishingCompany;
    @FXML
    private Button bAddBook;
    @FXML
    private TableColumn<?, ?> authorCol;
    @FXML
    private TextField tFFind;
    @FXML
    private TextField tFIdCallCard;
    @FXML
    private ComboBox<String> cBIdReader;
    @FXML
    private Button bAddCallCard;
    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> CategoryCol;
    @FXML
    private TextField tFNameReader;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TextField tFPublishingYear;
    @FXML
    private TableColumn<?, ?> ReprintTimesCol;
    @FXML
    private ComboBox<String> cBIdBook;
    @FXML
    private TableColumn<?, ?> PublishingCompanyCol;
    @FXML
    private TextField tFStaff;
    @FXML
    private TextField tFReprintTimes;
    @FXML
    private TextField tFPhoneNumber;
    @FXML
    private TextField tFAddress;
    @FXML
    private TextField tFBookLoanDay;
    @FXML
    private Button bSaveCallCard;
    @FXML
    private TextField tFCategory;
    @FXML
    private Button bCancelBook;
    @FXML
    private Button bExtend;
    @FXML
    private TextField tFAuthor;
    @FXML
    private Button bSaveBook;
    @FXML
    private TableColumn<?, ?> PublishingCol;
    @FXML
    private Button bCancelCallCard;
    @FXML
    private Button bFind;

    private void resetValueBook() {
        cBIdBook.setValue("");
        tFNameBook.setText("");
        tFAuthor.setText("");
        tFCategory.setText("");
        tFPublishingCompany.setText("");
        tFPublishingYear.setText("");
        tFReprintTimes.setText("");
        cBIdBook.setDisable(true);
        bAddBook.setDisable(true);
        bSaveBook.setDisable(true);
        bCancelBook.setDisable(true);
    }

    @FXML
    void handleOnKeyPressedReader(KeyEvent event) {
        Reader reader = ReaderManagementControl.getListReader().get(readerManagementControl.findIndexById(cBIdReader.getValue()));
        tFNameReader.setText(reader.getNameReader());
        tFAddress.setText(reader.getAddressReader());
        tFPhoneNumber.setText(reader.getPhoneNumber());
        FxUtilTest.getComboBoxValue(cBIdReader);
    }

    @FXML
    void handleOnKeyPressedBook(KeyEvent event) {
        Book book = BookManagementControl.getListBook().get(bookManagementControl.findIndexById(cBIdBook.getValue()));
        tFNameBook.setText(book.getNameBook());
        if (!book.getAuthor().equals("")) {
            tFAuthor.setText(book.getAuthor());
        }
        if (!book.getCategory().equals("")) {
            tFCategory.setText(book.getCategory());
        }
        if (!book.getPublishingCompany().equals("")) {
            tFPublishingCompany.setText(book.getPublishingCompany());
        }
        if (book.getPublishingYear() != null) {
            tFPublishingYear.setText(book.getPublishingYear().toString());
        }
        tFReprintTimes.setText(String.valueOf(book.getReprintTimes()));

        FxUtilTest.getComboBoxValue(cBIdBook);
    }

    private void resetValueForm() {
        resetValueBook();
        tFIdCallCard.setText("");
        cBIdReader.setValue("");
        tFNameReader.setText("");
        tFAddress.setText("");
        tFPhoneNumber.setText("");
        tFStaff.setText("");
        tFBookLoanDay.setText("");
        cBIdReader.setDisable(true);
        bAddCallCard.setDisable(false);
        bSaveCallCard.setDisable(true);
        bCancelCallCard.setDisable(true);
    }

    private String autoSetId() {
        LocalDateTime dateTime = LocalDateTime.now();
        return "CC" + DateTimeFormatter.formatDateTime(dateTime, DateTimeFormatter.getPatternDatetimeCreateId());
    }

    @FXML
    protected void handleRowSelect(ActionEvent event) {

    }

    @FXML
    protected void onAddButtonBookClick(ActionEvent event) {
        cBIdBook.setValue("");
        tFNameBook.setText("");
        tFAuthor.setText("");
        tFCategory.setText("");
        tFPublishingCompany.setText("");
        tFPublishingYear.setText("");
        tFReprintTimes.setText("");
        cBIdBook.setDisable(false);
        cBIdBook.setItems(books);
        FxUtilTest.autoCompleteComboBoxPlus(cBIdBook, (typedText, itemToCompare) -> itemToCompare.toUpperCase().contains(typedText.toUpperCase()));
        bAddBook.setDisable(true);
        bSaveBook.setDisable(false);
        bCancelBook.setDisable(false);
    }

    @FXML
    protected void onSaveButtonBookClick(ActionEvent event) {

    }

    @FXML
    protected void onCancelButtonBookClick(ActionEvent event) {
        resetValueBook();
        bAddBook.setDisable(false);
    }

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    protected void onAddButtonCallCardClick(ActionEvent event) {
        LocalDateTime dateTime = LocalDateTime.now();
        tFIdCallCard.setText(autoSetId());
        cBIdReader.setItems(readers);
        tFNameReader.setText("");
        tFAddress.setText("");
        tFPhoneNumber.setText("");
        tFStaff.setText(staffManagementControl.getCurrentStaff().getNameStaff());
        tFBookLoanDay.setText(DateTimeFormatter.formatDateTime(dateTime.plusMinutes(5), DateTimeFormatter.getPatternDatetime()));
        cBIdReader.setDisable(false);
        FxUtilTest.autoCompleteComboBoxPlus(cBIdReader, (typedText, itemToCompare) -> itemToCompare.toUpperCase().contains(typedText.toUpperCase()));
        onAddButtonBookClick(event);
        bAddCallCard.setDisable(true);
        bSaveCallCard.setDisable(false);
        bCancelCallCard.setDisable(false);
    }

    @FXML
    protected void onSaveButtonCallCardClick(ActionEvent event) {

    }

    @FXML
    protected void onFindButtonStaffClick(ActionEvent event) {

    }

    @FXML
    protected void onExitButtonClick(ActionEvent event) {

    }

    @FXML
    protected void onCancelButtonCallCardClick(ActionEvent event) {
        resetValueForm();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetValueForm();
    }
}
