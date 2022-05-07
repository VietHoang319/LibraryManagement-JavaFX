package com.example.librarymanagement.control;

import com.example.librarymanagement.file_handling.FileBookCSV;
import com.example.librarymanagement.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookManagenmentControl implements IManagement<Book>{
    private static final List<Book> listBook = new ArrayList<>();

    public BookManagenmentControl() {
        FileBookCSV.readFile(listBook);
    }

    public static List<Book> getListStaff() {
        return listBook;
    }

    @Override
    public void add(Book book) {
        listBook.add(book);
        FileBookCSV.writeFile(listBook);
    }

    @Override
    public void update(String id, Book book) {
        FileBookCSV.writeFile(listBook);
    }

    @Override
    public void delete(String id) {
        FileBookCSV.writeFile(listBook);
    }

    @Override
    public int findId(String id) {
        return 0;
    }
}
