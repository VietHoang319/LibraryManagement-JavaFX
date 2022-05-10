package com.example.librarymanagement.control;

import com.example.librarymanagement.file_handling.FileBookCSV;
import com.example.librarymanagement.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookManagementControl implements IManagement<Book>{
    private static final List<Book> listBook = new ArrayList<>();

    public BookManagementControl() {
        FileBookCSV.readFile(listBook);
    }

    public static List<Book> getListBook() {
        return listBook;
    }

    @Override
    public void add(Book book) {
        listBook.add(book);
        FileBookCSV.writeFile(listBook);
    }

    @Override
    public void update(String id, Book book) {
        listBook.set(findIndexById(id), book);
        FileBookCSV.writeFile(listBook);
    }

    @Override
    public void delete(String id) {
        listBook.remove(findIndexById(id));
        FileBookCSV.writeFile(listBook);
    }

    @Override
    public int findIndexById(String id) {
        for (int i = 0; i < listBook.size(); i++) {
            if (listBook.get(i).getIdBook().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public List<Book> findBookByIdOrNameOrCompanyOrAuthor(String inp) {
        List<Book> list = new ArrayList<>();
        for(Book book : listBook) {
            if (book.getIdBook().contains(inp) || book.getNameBook().contains(inp) || book.getAuthor().contains(inp) || book.getPublishingCompany().contains(inp)) {
                list.add(book);
            }
        }
        return list;
    }

    public List<String> getListId() {
        List<String> list = new ArrayList<>();
        for (Book book:listBook) {
            list.add(book.getIdBook());
        }
        return list;
    }
}
