package com.example.librarymanagement.file_handle;

import com.example.librarymanagement.model.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

public class FileCallCardInformationCSV {
    public static void writeFile(List<CallCardInfor> list) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src\\main\\java\\com\\example\\librarymanagement\\database\\CallCardInformationData.csv"))) {
            bufferedWriter.write("ID CALL CARD, ID BOOK, NUMBER OF LOAN BOOK, RETURN DEADLINE");
            bufferedWriter.newLine();
            for (CallCardInfor callCardInfor: list) {
                bufferedWriter.write(callCardInfor.getCallCard().getIdCallCard() + ","  + callCardInfor.getBook().getIdBook() + "," +
                        callCardInfor.getNumberOfLoanBook() + "," + callCardInfor.getReturnDeadline());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFile(List<CallCardInfor> callCardInforList, List<CallCard> callCards, List<Book> books) {
        String line = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\java\\com\\example\\librarymanagement\\database\\CallCardInformationData.csv"))){
            bufferedReader.readLine();
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String[] callCardInforArray = line.split(",");
                CallCard callCard = new CallCard();
                for (CallCard callCard1: callCards) {
                    if (callCard1.getIdCallCard().equals(callCardInforArray[0])) {
                        callCard = callCard1;
                        break;
                    }
                }

                Book book = new Book();
                for (Book book1: books) {
                    if (book1.getIdBook().equals(callCardInforArray[1])) {
                        book = book1;
                        break;
                    }
                }
                int numberOfReturnBook = Integer.parseInt(callCardInforArray[2]);
                LocalDateTime returnDeadline = LocalDateTime.parse(callCardInforArray[3]);
                callCardInforList.add(new CallCardInfor(callCard, book, numberOfReturnBook, returnDeadline));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
