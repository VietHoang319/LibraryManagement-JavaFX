package com.example.librarymanagement.model;

import java.time.LocalDateTime;

public class CallCardInfor {
    private CallCard callCard;
    private Book book;
    private LocalDateTime ReturnDeadline;

    public CallCardInfor() {
    }

    public CallCardInfor(CallCard callCard, Book book, LocalDateTime returnDeadline) {
        this.callCard = callCard;
        this.book = book;
        ReturnDeadline = returnDeadline;
    }

    public CallCard getCallCard() {
        return callCard;
    }

    public void setCallCard(CallCard callCard) {
        this.callCard = callCard;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getReturnDeadline() {
        return ReturnDeadline;
    }

    public void setReturnDeadline(LocalDateTime returnDeadline) {
        ReturnDeadline = returnDeadline;
    }
}
