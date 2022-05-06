package com.example.librarymanagement.model;

import java.time.LocalDate;

public class Reader {
    private String idReader;
    private String nameReader;
    private String addressReader;
    private String mailReader;
    private String phoneNumber;
    private LocalDate expiry;

    public Reader() {
    }

    public Reader(String idReader, String nameReader, String addressReader, String mailReader, String phoneNumber, LocalDate expiry) {
        this.idReader = idReader;
        this.nameReader = nameReader;
        this.addressReader = addressReader;
        this.mailReader = mailReader;
        this.phoneNumber = phoneNumber;
        this.expiry = expiry;
    }

    public String getIdReader() {
        return idReader;
    }

    public String getNameReader() {
        return nameReader;
    }

    public void setNameReader(String nameReader) {
        this.nameReader = nameReader;
    }

    public String getAddressReader() {
        return addressReader;
    }

    public void setAddressReader(String addressReader) {
        this.addressReader = addressReader;
    }

    public String getMailReader() {
        return mailReader;
    }

    public void setMailReader(String mailReader) {
        this.mailReader = mailReader;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }
}
