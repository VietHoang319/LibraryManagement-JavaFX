package com.example.librarymanagement.control;

import com.example.librarymanagement.file_handle.FileCallCardInformationCSV;
import com.example.librarymanagement.model.CallCardInfor;

import java.util.ArrayList;
import java.util.List;

public class CallCardInformationManagementControl {
    private static final List<CallCardInfor> callCardInformationList = new ArrayList<>();

    public CallCardInformationManagementControl() {
        FileCallCardInformationCSV.readFile(callCardInformationList, CallCardManagementControl.getCallCards(), BookManagementControl.getBooks());
    }

    public static List<CallCardInfor> getReturnCardInfors() {
        return callCardInformationList;
    }

    public void addCallCardInfor(CallCardInfor callCardInformation) {
        callCardInformationList.add(callCardInformation);
    }

    public void deleteCallCardInfor(String inp) {
        callCardInformationList.removeAll(findCallCardInforById(inp));
    }

    public void deleteCallCardInforByIdCallCardAndIdBook(String idCallCard, String idBook) {
        CallCardInfor callCardInfor = new CallCardInfor();
        for (CallCardInfor callCardInfor1: callCardInformationList) {
            if (callCardInfor1.getCallCard().getIdCallCard().equals(idCallCard) && callCardInfor1.getBook().getIdBook().equals(idBook)) {
                callCardInfor = callCardInfor1;
                break;
            }
        }
        callCardInformationList.remove(callCardInfor);
    }

    public List<CallCardInfor> findCallCardInforById(String inp) {
        List<CallCardInfor> list = new ArrayList<>();
        for (CallCardInfor callCardInfor: callCardInformationList) {
            if (callCardInfor.getCallCard().getIdCallCard().equals(inp)) {
                list.add(callCardInfor);
            }
        }
        return list;
    }
}
