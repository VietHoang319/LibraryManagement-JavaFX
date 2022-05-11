package com.example.librarymanagement.control;

import com.example.librarymanagement.file_handle.FileBookCSV;
import com.example.librarymanagement.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookManagementControl implements IManagement<Book>{
    private static final List<Book> books = new ArrayList<>();

    public BookManagementControl() {
        FileBookCSV.readFile(books);
    }

    public static List<Book> getBooks() {
        return books;
    }

    @Override
    public void add(Book book) {
        books.add(book);
        FileBookCSV.writeFile(books);
    }

    @Override
    public void update(String id, Book book) {
        books.set(findIndexById(id), book);
        FileBookCSV.writeFile(books);
    }

    @Override
    public void delete(String id) {
        books.remove(findIndexById(id));
        FileBookCSV.writeFile(books);
    }

    @Override
    public int findIndexById(String id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIdBook().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public List<Book> findBookByIdOrNameOrCompanyOrAuthor(String inp) {
        List<Book> list = new ArrayList<>();
        for(Book book : books) {
            if (book.getIdBook().contains(inp) || book.getNameBook().contains(inp) || book.getAuthor().contains(inp) || book.getPublishingCompany().contains(inp)) {
                list.add(book);
            }
        }
        return list;
    }

    public List<String> getListId() {
        List<String> list = new ArrayList<>();
        for (Book book: books) {
            list.add(book.getIdBook());
        }
        return list;
    }
}
