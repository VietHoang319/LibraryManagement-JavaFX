package com.example.librarymanagement.model;

import java.time.LocalDate;

public class ReturnCard {
    private String idCallCard;
    private String idBook;
    private int idStaff;
    private LocalDate returnDate;

    public ReturnCard() {
    }

    public ReturnCard(String idCallCard, String idBook, int idStaff, LocalDate returnDate) {
        this.idCallCard = idCallCard;
        this.idBook = idBook;
        this.idStaff = idStaff;
        this.returnDate = returnDate;
    }

    public String getIdCallCard() {
        return idCallCard;
    }

    public void setIdCallCard(String idCallCard) {
        this.idCallCard = idCallCard;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public int getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
