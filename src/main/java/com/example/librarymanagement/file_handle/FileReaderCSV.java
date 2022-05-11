package com.example.librarymanagement.file_handle;

import com.example.librarymanagement.model.Reader;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

public class FileReaderCSV {
    public static final String NULLVALUE = "";

    public static void writeFile(List<Reader> list) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src\\main\\java\\com\\example\\librarymanagement\\database\\ReaderData.csv"))) {
            bufferedWriter.write("ID, NAME, ADDRESS, EMAIL, PHONE NUMBER, EXPIRY, ISLOCK");
            bufferedWriter.newLine();
            for (Reader reader: list) {
                bufferedWriter.write(reader.getIdReader() + "," + reader.getNameReader() + "," + reader.getAddressReader() + ","
                        + reader.getEmailReader() + "," + reader.getPhoneNumber() + "," + reader.getExpiry() + "," + reader.isLock());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFile(List<Reader> list) {
        String line = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\java\\com\\example\\librarymanagement\\database\\ReaderData.csv"))){
            bufferedReader.readLine();
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String[] readerArray = line.split(",");
                String id = readerArray[0];
                String name = readerArray[1];
                String address;
                if (readerArray[2].equals(NULLVALUE)) {
                    address = NULLVALUE;
                } else {
                    address = readerArray[2];
                }
                String email;
                if (readerArray[3].equals(NULLVALUE)) {
                    email = NULLVALUE;
                } else {
                    email = readerArray[3];
                }
                String phoneNumber;
                if (readerArray[4].equals(NULLVALUE)) {
                    phoneNumber = NULLVALUE;
                } else {
                    phoneNumber = readerArray[4];
                }
                LocalDateTime expiry = LocalDateTime.parse(readerArray[5]);
                boolean lock = Boolean.parseBoolean(readerArray[6]);
                list.add(new Reader(id, name, address, email, phoneNumber, expiry, lock));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
