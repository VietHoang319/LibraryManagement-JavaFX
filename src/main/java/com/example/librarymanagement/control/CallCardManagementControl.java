package com.example.librarymanagement.control;

import com.example.librarymanagement.file_handling.FileCallCardCSV;
import com.example.librarymanagement.model.CallCard;

import java.util.ArrayList;
import java.util.List;

public class CallCardManagementControl {
    private static final List<CallCard> listCallCard = new ArrayList<>();

    public CallCardManagementControl() {
        FileCallCardCSV.readFile(listCallCard, ReaderManagementControl.getListReader(), StaffManagementControl.getListStaff());
    }

    public static List<CallCard> getReturnCards() {
        return listCallCard;
    }

    public void addCallCard(CallCard callCard) {
        listCallCard.add(callCard);
    }
}
