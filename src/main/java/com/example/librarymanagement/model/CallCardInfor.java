package com.example.librarymanagement.model;

import java.time.LocalDate;

public class CallCardInfor {
    private String idCallCard;
    private String idBook;
    private LocalDate ReturnDeadline;

    public CallCardInfor() {
    }

    public CallCardInfor(String idCallCard, String idBook, LocalDate returnDeadline) {
        this.idCallCard = idCallCard;
        this.idBook = idBook;
        ReturnDeadline = returnDeadline;
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

    public LocalDate getReturnDeadline() {
        return ReturnDeadline;
    }

    public void setReturnDeadline(LocalDate returnDeadline) {
        ReturnDeadline = returnDeadline;
    }
}
