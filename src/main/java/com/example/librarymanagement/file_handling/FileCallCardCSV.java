package com.example.librarymanagement.file_handling;

import com.example.librarymanagement.model.CallCard;
import com.example.librarymanagement.model.Reader;
import com.example.librarymanagement.model.Staff;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

public class FileCallCardCSV {

    public static void writeFile(List<CallCard> list) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src\\main\\java\\com\\example\\librarymanagement\\database\\CallCardData.csv"))) {
            bufferedWriter.write("ID CALL CARD, ID READER, ID STAFF, BOOK LOAN DAY");
            bufferedWriter.newLine();
            for (CallCard callCard: list) {
                bufferedWriter.write(callCard.getIdCallCard() + "," + callCard.getReader().getIdReader() + "," + callCard.getStaff().getIdStaff() + "," + callCard.getBookLoanDay());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFile(List<CallCard> listCallCard, List<Reader> listReader, List<Staff> listStaff) {
        String line = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\java\\com\\example\\librarymanagement\\database\\CallCardData.csv"))){
            bufferedReader.readLine();
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String[] callCardArray = line.split(",");
                String idCallCard = callCardArray[0];
                Reader reader = new Reader();
                for (Reader reader1: listReader) {
                    if (reader1.getIdReader().equals(callCardArray[1])) {
                        reader = reader1;
                        break;
                    }
                }
                Staff staff = new Staff();
                for (Staff staff1: listStaff) {
                    if (staff1.getIdStaff() == Integer.parseInt(callCardArray[2])) {
                        staff = staff1;
                        break;
                    }
                }
                LocalDateTime bookLoanDay = LocalDateTime.parse(callCardArray[3]);
                listCallCard.add(new CallCard(idCallCard, reader, staff, bookLoanDay));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
