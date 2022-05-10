package com.example.librarymanagement.control;

import com.example.librarymanagement.file_handling.FileCallCardInformationCSV;
import com.example.librarymanagement.model.CallCardInfor;

import java.util.ArrayList;
import java.util.List;

public class CallCardInformationManagement {
    private static final List<CallCardInfor> listCallCardInformation = new ArrayList<>();

    public CallCardInformationManagement() {

    }

    public static List<CallCardInfor> getReturnCards() {
        return listCallCardInformation;
    }

    public void addCallCard(CallCardInfor callCardInformation) {
        listCallCardInformation.add(callCardInformation);
    }
}
