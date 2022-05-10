package com.example.librarymanagement.file_handling;

import com.example.librarymanagement.model.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

public class FileCallCardInformationCSV {
    public static void writeFile(List<CallCardInfor> list) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src\\main\\java\\com\\example\\librarymanagement\\database\\CallCardInformationData.csv"))) {
            bufferedWriter.write("ID CALL CARD, ID BOOK, RETURN DEADLINE");
            bufferedWriter.newLine();
            for (CallCardInfor callCardInfor: list) {
                bufferedWriter.write(callCardInfor.getCallCard().getIdCallCard() + ","  + callCardInfor.getBook().getIdBook() + "," + callCardInfor.getReturnDeadline());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFile(List<CallCardInfor> listCallCardInfor, List<CallCard> listCallCard, List<Book> listBook) {
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
                for (CallCard callCard1: listCallCard) {
                    if (callCard1.getIdCallCard().equals(callCardInforArray[0])) {
                        callCard = callCard1;
                        break;
                    }
                }

                Book book = new Book();
                for (Book book1: listBook) {
                    if (book1.getIdBook().equals(callCardInforArray[1])) {
                        book = book1;
                        break;
                    }
                }

                LocalDateTime returnDeadline = LocalDateTime.parse(callCardInforArray[2]);
                listCallCardInfor.add(new CallCardInfor(callCard, book, returnDeadline));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
