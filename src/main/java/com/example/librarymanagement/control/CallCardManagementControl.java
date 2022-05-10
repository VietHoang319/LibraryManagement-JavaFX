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

    public void deleteCallCard(String id) {
        listCallCard.remove(findIndexById(id));
    }

    public int findIndexById(String id) {
        for (int i = 0; i < listCallCard.size(); i++) {
            if (listCallCard.get(i).getIdCallCard().equals(id)) {
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
            return listCallCard.get(index);
        }
    }
}
