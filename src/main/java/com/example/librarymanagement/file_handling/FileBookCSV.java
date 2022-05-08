package com.example.librarymanagement.file_handling;

import com.example.librarymanagement.model.Book;

import java.io.*;
import java.time.Year;
import java.util.List;

public class FileBookCSV {
    public static final String NULLVALUE = "";

    public static void writeFile(List<Book> list) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src\\main\\java\\com\\example\\librarymanagement\\database\\BookData.csv"))) {
            bufferedWriter.write("ID, NAME, AUTHOR, CATEGORY, PUBLISHING COMPANY, PUBLISHING YEAR, REPRINT TIMES, NUMBER OF BOOK");
            bufferedWriter.newLine();
            for (Book book: list) {
                bufferedWriter.write(book.getIdBook() + "," + book.getNameBook() + "," + book.getAuthor() + "," + book.getCategory() + ","
                        + book.getPublishingCompany() + "," + book.getPublishingYear() + "," + book.getReprintTimes() + "," + book.getNumberOfBook());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFile(List<Book> list) {
        String line = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\main\\java\\com\\example\\librarymanagement\\database\\BookData.csv"))){
            bufferedReader.readLine();
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String[] bookArray = line.split(",");
                String id = bookArray[0];
                String name = bookArray[1];
                String author;
                if (bookArray[2].equals(NULLVALUE)) {
                    author = NULLVALUE;
                } else {
                    author = bookArray[2];
                }
                String category;
                if (bookArray[3].equals(NULLVALUE)) {
                    category = NULLVALUE;
                } else {
                    category = bookArray[3];
                }
                String publishingCompany;
                if (bookArray[4].equals(NULLVALUE)) {
                    publishingCompany = NULLVALUE;
                } else {
                    publishingCompany = bookArray[4];
                }
                Year publishingYear;
                if (bookArray[5].equals("null")) {
                    publishingYear = null;
                } else {
                    publishingYear = Year.parse(bookArray[5]);
                }
                int reprintTimes;
                if (bookArray[6].equals(NULLVALUE)) {
                    reprintTimes = 0;
                } else {
                    reprintTimes = Integer.parseInt(bookArray[6]);
                }
                int numberOfBook = Integer.parseInt(bookArray[7]);
                list.add(new Book(id, name, author, category, publishingCompany, publishingYear, reprintTimes, numberOfBook));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
