package com.example.librarymanagement.model;

import java.time.LocalDate;

public class CallCard {
    private String idCallCard;
    private String idReader;
    private int idStaff;
    private LocalDate bookLoanDay;

    public CallCard() {
    }

    public CallCard(String idCallCard, String idReader, int idStaff, LocalDate bookLoanDay) {
        this.idCallCard = idCallCard;
        this.idReader = idReader;
        this.idStaff = idStaff;
        this.bookLoanDay = bookLoanDay;
    }

    public String getIdCallCard() {
        return idCallCard;
    }

    public void setIdCallCard(String idCallCard) {
        this.idCallCard = idCallCard;
    }

    public String getIdReader() {
        return idReader;
    }

    public void setIdReader(String idReader) {
        this.idReader = idReader;
    }

    public int getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    public LocalDate getBookLoanDay() {
        return bookLoanDay;
    }

    public void setBookLoanDay(LocalDate bookLoanDay) {
        this.bookLoanDay = bookLoanDay;
    }
}
