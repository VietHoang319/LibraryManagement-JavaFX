package com.example.librarymanagement.model;

import java.time.LocalDateTime;

public class CallCardInfor {
    private CallCard callCard;
    private Book book;

    private int numberOfLoanBook;

    private LocalDateTime returnDeadline;

    public CallCardInfor() {
    }

    public CallCardInfor(CallCard callCard, Book book, int numberOfLoanBook, LocalDateTime returnDeadline) {
        this.callCard = callCard;
        this.book = book;
        this.numberOfLoanBook = numberOfLoanBook;
        this.returnDeadline = returnDeadline;
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
        return returnDeadline;
    }

    public void setReturnDeadline(LocalDateTime returnDeadline) {
        this.returnDeadline = returnDeadline;
    }

    public int getNumberOfLoanBook() {
        return numberOfLoanBook;
    }

    public void setNumberOfLoanBook(int numberOfLoanBook) {
        this.numberOfLoanBook = numberOfLoanBook;
    }
}
