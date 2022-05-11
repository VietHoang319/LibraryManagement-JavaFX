package com.example.librarymanagement.control;

import com.example.librarymanagement.file_handle.FileCallCardInformationCSV;
import com.example.librarymanagement.model.CallCardInfor;

import java.util.ArrayList;
import java.util.List;

public class CallCardInformationManagementControl {
    private static final List<CallCardInfor> callCardInforList = new ArrayList<>();

    public CallCardInformationManagementControl() {
        FileCallCardInformationCSV.readFile(callCardInforList, CallCardManagementControl.getCallCards(), BookManagementControl.getBooks());
    }

    public static List<CallCardInfor> getCallCardInforList() {
        return callCardInforList;
    }

    public void addCallCardInfor(CallCardInfor callCardInformation) {
        callCardInforList.add(callCardInformation);
    }

    public void deleteCallCardInfor(String inp) {
        callCardInforList.removeAll(findCallCardInforById(inp));
    }

    public void deleteCallCardInforByIdCallCardAndIdBook(String idCallCard, String idBook) {
        CallCardInfor callCardInfor = new CallCardInfor();
        for (CallCardInfor callCardInfor1: callCardInforList) {
            if (callCardInfor1.getCallCard().getIdCallCard().equals(idCallCard) && callCardInfor1.getBook().getIdBook().equals(idBook)) {
                callCardInfor = callCardInfor1;
                break;
            }
        }
        callCardInforList.remove(callCardInfor);
    }

    public List<CallCardInfor> findCallCardInforById(String inp) {
        List<CallCardInfor> list = new ArrayList<>();
        for (CallCardInfor callCardInfor: callCardInforList) {
            if (callCardInfor.getCallCard().getIdCallCard().equals(inp)) {
                list.add(callCardInfor);
            }
        }
        return list;
    }
}
