package com.example.librarymanagement.file_handle;

import com.example.librarymanagement.model.*;
import com.example.librarymanagement.model.Reader;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

public class FileReturnCardCSV {
    public static void writeFile(List<ReturnCard> list) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src\\main\\java\\com\\example\\librarymanagement\\database\\ReturnCardData.csv"))) {
            bufferedWriter.write("ID CALL CARD, ID BOOK, ID STAFF, RETURN DATE");
            bufferedWriter.newLine();
            for (ReturnCard returnCard : list) {
                bufferedWriter.write(returnCard.getCallCard().getIdCallCard() + "," + returnCard.getBook().getIdBook() + "," + returnCard.getStaff().getIdStaff() + "," + returnCard.getReturnDate());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFile(List<ReturnCard> returnCards, List<CallCard> callCards, List<Staff> staffs, List<Book> books) {
        String line = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\java\\com\\example\\librarymanagement\\database\\ReturnCardData.csv"))) {
            bufferedReader.readLine();
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String[] returnCardArray = line.split(",");
                CallCard callCard = new CallCard();
                for (CallCard callCard1 : callCards) {
                    if (callCard1.getIdCallCard().equals(returnCardArray[0])) {
                        callCard = callCard1;
                        break;
                    }
                }
                Book book = new Book();
                for (Book book1 : books) {
                    if (book1.getIdBook().equals(returnCardArray[1])) {
                        book = book1;
                        break;
                    }
                }
                Staff staff = new Staff();
                for (Staff staff1 : staffs) {
                    if (staff1.getIdStaff() == Integer.parseInt(returnCardArray[2])) {
                        staff = staff1;
                        break;
                    }
                }
                LocalDateTime bookLoanDay = LocalDateTime.parse(returnCardArray[3]);
                returnCards.add(new ReturnCard(callCard, book, staff, bookLoanDay));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
