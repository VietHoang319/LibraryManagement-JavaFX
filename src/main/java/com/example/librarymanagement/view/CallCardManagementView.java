package com.example.librarymanagement.view;

import com.example.librarymanagement.FxUtilTest;
import com.example.librarymanagement.LibraryManagementApplication;
import com.example.librarymanagement.ThreadHandle;
import com.example.librarymanagement.control.*;
import com.example.librarymanagement.datetime.DateTimeFormatter;
import com.example.librarymanagement.file_handle.FileBookCSV;
import com.example.librarymanagement.file_handle.FileCallCardCSV;
import com.example.librarymanagement.file_handle.FileCallCardInformationCSV;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.CallCard;
import com.example.librarymanagement.model.CallCardInfor;
import com.example.librarymanagement.model.Reader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class CallCardManagementView implements Initializable {
    public static final String NULL_VALUE = "";
    private static Stage stage = MenuView.stage;
    private static final ReaderManagementControl readerManagementControl = ThreadHandle.getReaderManagementControl();
    private static final BookManagementControl bookManagementControl = ThreadHandle.getBookManagementControl();
    private static final StaffManagementControl staffManagementControl = ThreadHandle.getStaffManagementControl();
    private static final CallCardManagementControl callCardManagementControl = ThreadHandle.getCallCardManagementControl();
    private static final CallCardInformationManagementControl callCardInformationManagementControl = ThreadHandle.getCallCardInformationManagementControl();
    private static final ObservableList<String> readers = FXCollections.observableArrayList(readerManagementControl.getListId());
    private static final ObservableList<String> books = FXCollections.observableArrayList(bookManagementControl.getListId());
    private CallCard currentCallCard;
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
    private TextField tFNumberLoanBook;
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
        tFNumberLoanBook.setText("");
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
        tFNumberLoanBook.setDisable(true);
    }

    @FXML
    void handleOnKeyPressedReader(KeyEvent event) {
        Reader reader = ReaderManagementControl.getReaders().get(readerManagementControl.findIndexById(cBIdReader.getValue()));
        if (!reader.isLock()) {
            tFNameReader.setText(reader.getNameReader());
            tFAddress.setText(reader.getAddressReader());
            tFPhoneNumber.setText(reader.getPhoneNumber());
            FxUtilTest.getComboBoxValue(cBIdReader);
        } else {
            showAlert("Thẻ của độc giả này đã bị khóa");
        }
    }

    @FXML
    void handleOnKeyPressedBook(KeyEvent event) {
        Book book = BookManagementControl.getBooks().get(bookManagementControl.findIndexById(cBIdBook.getValue()));
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
        tVCallCard.setItems(null);
    }

    private String autoSetId() {
        LocalDateTime dateTime = LocalDateTime.now();
        return "CC" + DateTimeFormatter.formatDateTime(dateTime, DateTimeFormatter.getPatternDatetimeCreateId());
    }

    @FXML
    protected void handleRowSelect(MouseEvent event) {
        CallCardInfor row = tVCallCard.getSelectionModel().getSelectedItem();
        if (row != null) {
            if (event.getClickCount() == 1) {
                cBIdBook.setValue(row.getBook().getIdBook());
                tFNameBook.setText(row.getBook().getNameBook());
                tFAuthor.setText(row.getBook().getAuthor());
                tFCategory.setText(row.getBook().getCategory());
                tFPublishingCompany.setText(row.getBook().getPublishingCompany());
                String year;
                if (String.valueOf(row.getBook().getPublishingYear()).equals("null")) {
                    year = "";
                } else {
                    year = String.valueOf(row.getBook().getPublishingYear());
                }
                tFPublishingYear.setText(year);
                tFReprintTimes.setText(String.valueOf(row.getBook().getReprintTimes()));
                tFNumberLoanBook.setText(String.valueOf(row.getNumberOfLoanBook()));
                tFReturnDeadline.setText(DateTimeFormatter.formatDateTime(row.getReturnDeadline(), DateTimeFormatter.getPatternDatetime()));
            }
            if (event.getClickCount() == 2) {
                for (CallCardInfor callCardInfor: callCardInformationManagementControl.findCallCardInforById(tFIdCallCard.getText())) {
                    Book book = callCardInfor.getBook();
                    if (book.getIdBook().equals(cBIdBook.getValue())) {
                        book.setNumberOfBook(book.getNumberOfBook() + callCardInfor.getNumberOfLoanBook());
                    }
                }
                callCardInformationManagementControl.deleteCallCardInforByIdCallCardAndIdBook(tFIdCallCard.getText(), cBIdBook.getValue());
                showDataInTableView(callCardInformationManagementControl.findCallCardInforById(tFIdCallCard.getText()));
                resetValueBook();
            }
        }
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
        tFNumberLoanBook.setDisable(false);
    }

    @FXML
    protected void onSaveButtonBookClick(ActionEvent event) {
        int numBook = BookManagementControl.getBooks().get(bookManagementControl.findIndexById(cBIdBook.getValue())) .getNumberOfBook();
        try {
            if (numBook < Integer.parseInt(tFNumberLoanBook.getText())) {
                throw new Exception();
            }
            Book book = BookManagementControl.getBooks().get(bookManagementControl.findIndexById(cBIdBook.getValue()));
            CallCardInfor currentCallCardInfor = new CallCardInfor(currentCallCard, book, Integer.parseInt(tFNumberLoanBook.getText()), DateTimeFormatter.parseDatetime(tFReturnDeadline.getText(), DateTimeFormatter.getPatternDatetime()));
            callCardInformationManagementControl.addCallCardInfor(currentCallCardInfor);
            book.setNumberOfBook(book.getNumberOfBook() - Integer.parseInt(tFNumberLoanBook.getText()));
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
        tFNumberLoanBook.setDisable(false);

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
            if (callCardInformationManagementControl.findCallCardInforById(tFIdCallCard.getText()).size() != 0) {
                Reader reader = ReaderManagementControl.getReaders().get(readerManagementControl.findIndexById(cBIdReader.getValue()));
                currentCallCard.setReader(reader);
                FileCallCardCSV.writeFile(CallCardManagementControl.getCallCards());
                FileCallCardInformationCSV.writeFile(CallCardInformationManagementControl.getCallCardInforList());
                FileBookCSV.writeFile(BookManagementControl.getBooks());
                resetValueForm();
            } else {
                showAlert("Bạn không chưa mượn gì cả");
            }

        }
    }

    @FXML
    protected void onFindButtonStaffClick(ActionEvent event) {
        resetValueForm();
        String text = tFFind.getText().toUpperCase().trim();
        if(text.equals(NULL_VALUE)){
            resetValueForm();
        }
        else {
            CallCard callCard = callCardManagementControl.findCallCardById(text);
            if(callCard == null) {
                showAlert("Không tìm thấy");
                tFFind.setText("");
            } else {
                tFIdCallCard.setText(callCard.getIdCallCard());
                cBIdReader.setValue(callCard.getReader().getIdReader());
                tFAddress.setText(callCard.getReader().getAddressReader());
                tFPhoneNumber.setText(callCard.getReader().getPhoneNumber());
                tFStaff.setText(callCard.getStaff().getNameStaff());
                tFBookLoanDay.setText(DateTimeFormatter.formatDateTime(callCard.getBookLoanDay(), DateTimeFormatter.getPatternDatetime()));
                showDataInTableView(callCardInformationManagementControl.findCallCardInforById(tFIdCallCard.getText()));
            }
        }
    }

    @FXML
    protected void onExitButtonClick(ActionEvent event) {
        exit();
    }

    @FXML
    protected void onCancelButtonCallCardClick(ActionEvent event) {
        for (CallCardInfor callCardInfor: callCardInformationManagementControl.findCallCardInforById(tFIdCallCard.getText())) {
            Book book = callCardInfor.getBook();
            book.setNumberOfBook(book.getNumberOfBook() + callCardInfor.getNumberOfLoanBook());
        }
        callCardInformationManagementControl.deleteCallCardInfor(tFIdCallCard.getText());
        callCardManagementControl.deleteCallCard(tFIdCallCard.getText());
        resetValueForm();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetValueForm();
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