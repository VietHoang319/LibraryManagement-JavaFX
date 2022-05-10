package com.example.librarymanagement.control;

import com.example.librarymanagement.file_handling.FileCallCardInformationCSV;
import com.example.librarymanagement.model.CallCardInfor;

import java.util.ArrayList;
import java.util.List;

public class CallCardInformationManagementControl {
    private static final List<CallCardInfor> listCallCardInformation = new ArrayList<>();

    public CallCardInformationManagementControl() {
        FileCallCardInformationCSV.readFile(listCallCardInformation, CallCardManagementControl.getReturnCards(), BookManagementControl.getListBook());
    }

    public static List<CallCardInfor> getReturnCardInfors() {
        return listCallCardInformation;
    }

    public void addCallCardInfor(CallCardInfor callCardInformation) {
        listCallCardInformation.add(callCardInformation);
    }

    public void deleteCallCardInfor(String inp) {
        listCallCardInformation.removeAll(findCallCardInforById(inp));
    }

    public List<CallCardInfor> findCallCardInforById(String inp) {
        List<CallCardInfor> list = new ArrayList<>();
        for (CallCardInfor callCardInfor: listCallCardInformation) {
            if (callCardInfor.getCallCard().getIdCallCard().equals(inp)) {
                list.add(callCardInfor);
            }
        }
        return list;
    }
}
