package com.example.librarymanagement.view;

import com.example.librarymanagement.FxUtilTest;
import com.example.librarymanagement.control.*;
import com.example.librarymanagement.datetime.DateTimeFormatter;
import com.example.librarymanagement.file_handling.FileBookCSV;
import com.example.librarymanagement.file_handling.FileCallCardCSV;
import com.example.librarymanagement.file_handling.FileCallCardInformationCSV;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.CallCard;
import com.example.librarymanagement.model.CallCardInfor;
import com.example.librarymanagement.model.Reader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class CallCardManagementView implements Initializable {
    private final ReaderManagementControl readerManagementControl = new ReaderManagementControl();
    private final BookManagementControl bookManagementControl = new BookManagementControl();
    private final StaffManagementControl staffManagementControl = MenuView.staffManagementControl;
    private final CallCardManagementControl callCardManagementControl = new CallCardManagementControl();
    private final CallCardInformationManagementControl callCardInformationManagementControl = new CallCardInformationManagementControl();
    private final ObservableList<String> readers = FXCollections.observableArrayList(readerManagementControl.getListId());
    private final ObservableList<String> books = FXCollections.observableArrayList(bookManagementControl.getListId());
    private CallCard currentCallCard;
    private CallCardInfor currentCallCardInfor;
    @FXML
    private TextField tFNameBook;
    @FXML
    private TextField tFPublishingCompany;
    @FXML
    private Button bAddBook;
    @FXML
    private TextField tFFind;
    @FXML
    private TextField tFIdCallCard;
    @FXML
    private ComboBox<String> cBIdReader;
    @FXML
    private TextField tFReturnDeadline;
    @FXML
    private Button bAddCallCard;
    @FXML
    private TableColumn<CallCardInfor, String> idCol;
    @FXML
    private TextField tFNameReader;
    @FXML
    private TextField tFPublishingYear;
    @FXML
    private TextField tFNumberLoadBook;
    @FXML
    private ComboBox<String> cBIdBook;
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
    private Button bExit;
    @FXML
    private TextField tFAuthor;
    @FXML
    private Button bSaveBook;
    @FXML
    private TableColumn<CallCardInfor, Integer> numberLoanBookCol;
    @FXML
    private Button bCancelCallCard;
    @FXML
    private Button bFind;
    @FXML
    private TableView<CallCardInfor> tVCallCard;

    private void showDataInTableView(List<CallCardInfor> list) {
        ObservableList<CallCardInfor> listCallCardInfor = FXCollections.observableArrayList(list);
        idCol.setCellValueFactory(new PropertyValueFactory<CallCardInfor, String>("book"));
        numberLoanBookCol.setCellValueFactory(new PropertyValueFactory<CallCardInfor, Integer>("numberOfLoanBook"));
        tVCallCard.setItems(listCallCardInfor);
    }

    private void resetValueBook() {
        tFNumberLoadBook.setText("");
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
        tFNumberLoadBook.setDisable(true);
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
        tFReturnDeadline.setText("");
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
        tFNumberLoadBook.setDisable(false);
    }

    @FXML
    protected void onSaveButtonBookClick(ActionEvent event) {
        int numBook = BookManagementControl.getListBook().get(bookManagementControl.findIndexById(cBIdBook.getValue())) .getNumberOfBook();
        try {
            if (numBook < Integer.parseInt(tFNumberLoadBook.getText())) {
                throw new Exception();
            }
            Book book = BookManagementControl.getListBook().get(bookManagementControl.findIndexById(cBIdBook.getValue()));
            currentCallCardInfor = new CallCardInfor(currentCallCard, book, Integer.parseInt(tFNumberLoadBook.getText()), DateTimeFormatter.parseDatetime(tFReturnDeadline.getText(), DateTimeFormatter.getPatternDatetime()));
            callCardInformationManagementControl.addCallCardInfor(currentCallCardInfor);
            book.setNumberOfBook(book.getNumberOfBook() - Integer.parseInt(tFNumberLoadBook.getText()));
            showDataInTableView(callCardInformationManagementControl.findCallCardInforById(tFIdCallCard.getText()));
            resetValueBook();
            bAddBook.setDisable(false);
        }
        catch (NumberFormatException e) {
            showAlert("Bạn phải nhập số!");
        }
        catch (Exception e) {
            showAlert("Sách bạn chọn còn " + numBook + " quyển!");
        }
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
        tFReturnDeadline.setText(DateTimeFormatter.formatDateTime(dateTime.plusMinutes(5), DateTimeFormatter.getPatternDatetime()));
        tFBookLoanDay.setText(DateTimeFormatter.formatDateTime(dateTime, DateTimeFormatter.getPatternDatetime()));
        cBIdReader.setDisable(false);
        // Tự động hiện các giá trị trong combobox
        FxUtilTest.autoCompleteComboBoxPlus(cBIdReader, (typedText, itemToCompare) -> itemToCompare.toUpperCase().contains(typedText.toUpperCase()));
        onAddButtonBookClick(event);
        bAddCallCard.setDisable(true);
        bSaveCallCard.setDisable(false);
        bCancelCallCard.setDisable(false);
        tFNumberLoadBook.setDisable(false);

        currentCallCard = new CallCard(tFIdCallCard.getText(), null, staffManagementControl.getCurrentStaff(),
                DateTimeFormatter.parseDatetime(tFBookLoanDay.getText(), DateTimeFormatter.getPatternDatetime()));
        callCardManagementControl.addCallCard(currentCallCard);
    }

    @FXML
    protected void onSaveButtonCallCardClick(ActionEvent event) {
        if (cBIdReader.getValue().equals("")) {
            showAlert("Bạn phải chọn độc giả");
        }
        else {
            Reader reader = ReaderManagementControl.getListReader().get(readerManagementControl.findIndexById(cBIdReader.getValue()));
            currentCallCard.setReader(reader);
            FileCallCardCSV.writeFile(CallCardManagementControl.getReturnCards());
            FileCallCardInformationCSV.writeFile(CallCardInformationManagementControl.getReturnCardInfors());
            FileBookCSV.writeFile(BookManagementControl.getListBook());
            resetValueForm();
        }
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
