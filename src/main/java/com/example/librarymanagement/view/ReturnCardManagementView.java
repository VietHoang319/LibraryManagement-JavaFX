package com.example.librarymanagement.view;

import com.example.librarymanagement.LibraryManagementApplication;
import com.example.librarymanagement.ThreadHandle;
import com.example.librarymanagement.control.*;
import com.example.librarymanagement.datetime.DateTimeFormatter;
import com.example.librarymanagement.file_handle.FileBookCSV;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.CallCard;
import com.example.librarymanagement.model.CallCardInfor;
import com.example.librarymanagement.model.ReturnCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReturnCardManagementView implements Initializable {
    public static final String NULL_VALUE = "";
    private static final MenuView menuView = new MenuView();
    private static Stage stage = MenuView.stage;
    private static final BookManagementControl bookManagementControl = ThreadHandle.getBookManagementControl();
    private static final StaffManagementControl staffManagementControl = ThreadHandle.getStaffManagementControl();
    private static final CallCardManagementControl callCardManagementControl = ThreadHandle.getCallCardManagementControl();
    private static final CallCardInformationManagementControl callCardInformationManagementControl = ThreadHandle.getCallCardInformationManagementControl();
    private static final ReturnCardManagementControl returnCardManagementControl = ThreadHandle.getReturnCardManagementControl();
    @FXML
    private TextField tFNameBook;
    @FXML
    private TextField tFPublishingCompany;
    @FXML
    private TextField tFStaff;
    @FXML
    private Button bGiveBack;
    @FXML
    private TextField tFNumberLoanBook;
    @FXML
    private TextField tFReprintTimes;
    @FXML
    private TextField tFFind;
    @FXML
    private Button bExit;
    @FXML
    private TextField tFIdCallCard;
    @FXML
    private TextField tFCategory;
    @FXML
    private TableColumn<CallCardInfor, Integer> numberOfBookCol;
    @FXML
    private TextField tFReturnDay;
    @FXML
    private TableView<CallCardInfor> tVReturnCard;
    @FXML
    private TableColumn<CallCardInfor, String> idCol;
    @FXML
    private TextField tFAuthor;
    @FXML
    private TextField tFNameReader;
    @FXML
    private TextField tFPublishingYear;
    @FXML
    private TextField tFIdBook;
    @FXML
    private TextField tFIdReader;
    @FXML
    private TextField tFBookLoanDay;
    @FXML
    private Button bFind;

    private void resetValue() {
        tFIdBook.setText("");
        tFNameBook.setText("");
        tFAuthor.setText("");
        tFCategory.setText("");
        tFPublishingCompany.setText("");
        tFPublishingYear.setText("");
        tFReprintTimes.setText("");
        tFNumberLoanBook.setText("");
        bGiveBack.setDisable(true);
    }

    private void resetValueForm() {
        resetValue();
        tFIdCallCard.setText("");
        tFIdReader.setText("");
        tFNameReader.setText("");
        tFStaff.setText("");
        tFReturnDay.setText("");
        tFBookLoanDay.setText("");
        tVReturnCard.setItems(null);
    }

    private void showDataInTableView(List<CallCardInfor> list) {
        ObservableList<CallCardInfor> callCardInfors = FXCollections.observableArrayList(list);
        idCol.setCellValueFactory(new PropertyValueFactory<CallCardInfor, String>("book"));
        numberOfBookCol.setCellValueFactory(new PropertyValueFactory<CallCardInfor, Integer>("numberOfLoanBook"));
        tVReturnCard.setItems(callCardInfors);
    }

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private List<CallCardInfor> compareList() {
        List<CallCardInfor> callCardInfors = callCardInformationManagementControl.findCallCardInforById(tFIdCallCard.getText());
        List<CallCardInfor> callCardInforsCompare = new ArrayList<>();

        List<ReturnCard> returnCards = returnCardManagementControl.findListReturnCardById(tFIdCallCard.getText());
        for (CallCardInfor callCardInfor : callCardInfors) {
            for (ReturnCard returnCard : returnCards) {
                if (callCardInfor.getBook().getIdBook().equals(returnCard.getBook().getIdBook())) {
                    callCardInforsCompare.add(callCardInfor);
                }
            }
        }

        callCardInfors.removeAll(callCardInforsCompare);
        return callCardInfors;
    }

    @FXML
    protected void handleRowSelect() {
        CallCardInfor row = tVReturnCard.getSelectionModel().getSelectedItem();
        if (row != null) {
            tFIdBook.setText(row.getBook().getIdBook());
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
            bGiveBack.setDisable(false);
        }
    }

    @FXML
    protected void onFindButtonClick(ActionEvent event) {
        resetValueForm();
        String text = tFFind.getText().toUpperCase().trim();
        if (text.equals(NULL_VALUE)) {
            resetValueForm();
        } else {
            CallCard callCard = callCardManagementControl.findCallCardById(text);
            if (callCard == null) {
                showAlert("Không tìm thấy");
            } else {
                tFIdCallCard.setText(callCard.getIdCallCard());
                tFIdReader.setText(callCard.getReader().getIdReader());
                tFNameReader.setText(callCard.getReader().getNameReader());
//                tFStaff.setText(callCard.getStaff().getNameStaff());
                tFStaff.setText(staffManagementControl.getCurrentStaff().getNameStaff());
                tFBookLoanDay.setText(DateTimeFormatter.formatDateTime(callCard.getBookLoanDay(), DateTimeFormatter.getPatternDatetime()));
                tFReturnDay.setText(DateTimeFormatter.formatDateTime(LocalDateTime.now(), DateTimeFormatter.getPatternDatetime()));
                showDataInTableView(compareList());
            }
            tFFind.setText("");
        }
    }

    @FXML
    protected void onGiveBackButtonClick(ActionEvent event) {
        CallCard callCard = callCardManagementControl.findCallCardById(tFIdCallCard.getText());
        Book book = BookManagementControl.getBooks().get(bookManagementControl.findIndexById(tFIdBook.getText()));
        LocalDateTime returnDate = DateTimeFormatter.parseDatetime(tFReturnDay.getText(), DateTimeFormatter.getPatternDatetime());
        returnCardManagementControl.add(new ReturnCard(callCard, book, staffManagementControl.getCurrentStaff(), returnDate));
        book.setNumberOfBook(book.getNumberOfBook() + Integer.parseInt(tFNumberLoanBook.getText()));
        FileBookCSV.writeFile(BookManagementControl.getBooks());
        if (compareList().size() != 0) {
            showDataInTableView(compareList());
            resetValue();
        } else {
            resetValueForm();
        }
    }

    @FXML
    protected void onExitButtonClick(ActionEvent event) {
        exit();
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
