package com.example.librarymanagement.model;

import java.time.LocalDate;
import java.time.Year;

public class Book {
    private String idBook;
    private String nameBook;
    private String author;
    private String category;
    private String publishingCompany;
    private Year publishingYear;
    private int reprintTimes;
    private int numberOfBook;

    public Book() {
    }

    public Book(String idBook, String nameBook, String author, String category, String publishingCompany, Year publishingYear, int reprintTimes, int numberOfBook) {
        this.idBook = idBook;
        this.nameBook = nameBook;
        this.author = author;
        this.category = category;
        this.publishingCompany = publishingCompany;
        this.publishingYear = publishingYear;
        this.reprintTimes = reprintTimes;
        this.numberOfBook = numberOfBook;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public Year getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(Year publishingYear) {
        this.publishingYear = publishingYear;
    }

    public int getReprintTimes() {
        return reprintTimes;
    }

    public void setReprintTimes(int reprintTimes) {
        this.reprintTimes = reprintTimes;
    }

    public int getNumberOfBook() {
        return numberOfBook;
    }

    public void setNumberOfBook(int numberOfBook) {
        this.numberOfBook = numberOfBook;
    }

    @Override
    public String toString() {
        return idBook;
    }
}
