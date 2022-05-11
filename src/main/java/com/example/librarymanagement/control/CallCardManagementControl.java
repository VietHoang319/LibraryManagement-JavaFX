package com.example.librarymanagement.control;

import com.example.librarymanagement.file_handle.FileCallCardCSV;
import com.example.librarymanagement.model.CallCard;

import java.util.ArrayList;
import java.util.List;

public class CallCardManagementControl {
    private static final List<CallCard> callCards = new ArrayList<>();

    public CallCardManagementControl() {
        FileCallCardCSV.readFile(callCards, ReaderManagementControl.getReaders(), StaffManagementControl.getStaffs());
    }

    public static List<CallCard> getCallCards() {
        return callCards;
    }

    public void addCallCard(CallCard callCard) {
        callCards.add(callCard);
    }

    public void deleteCallCard(String id) {
        callCards.remove(findIndexById(id));
    }

    public int findIndexById(String id) {
        for (int i = 0; i < callCards.size(); i++) {
            if (callCards.get(i).getIdCallCard().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public CallCard findCallCardById(String id) {
        int index = findIndexById(id);
        if (index == -1) {
            return null;
        } else {
            return callCards.get(index);
        }
    }
}
