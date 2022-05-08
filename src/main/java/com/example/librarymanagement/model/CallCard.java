package com.example.librarymanagement.model;

import java.time.LocalDateTime;

public class CallCard {
    private String idCallCard;
    private Reader reader;
    private Staff staff;
    private LocalDateTime bookLoanDay;

    public CallCard() {
    }

    public CallCard(String idCallCard, Reader reader, Staff staff, LocalDateTime bookLoanDay) {
        this.idCallCard = idCallCard;
        this.reader = reader;
        this.staff = staff;
        this.bookLoanDay = bookLoanDay;
    }

    public String getIdCallCard() {
        return idCallCard;
    }

    public void setIdCallCard(String idCallCard) {
        this.idCallCard = idCallCard;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public LocalDateTime getBookLoanDay() {
        return bookLoanDay;
    }

    public void setBookLoanDay(LocalDateTime bookLoanDay) {
        this.bookLoanDay = bookLoanDay;
    }
}
