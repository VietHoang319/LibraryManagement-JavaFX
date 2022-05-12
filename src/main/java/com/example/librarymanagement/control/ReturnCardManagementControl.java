package com.example.librarymanagement.control;

import com.example.librarymanagement.file_handle.FileReturnCardCSV;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.CallCard;
import com.example.librarymanagement.model.ReturnCard;

import java.util.ArrayList;
import java.util.List;

public class ReturnCardManagementControl {
    private static final List<ReturnCard> returnCards = new ArrayList<>();

    public ReturnCardManagementControl() {
        FileReturnCardCSV.readFile(returnCards, CallCardManagementControl.getCallCards(), StaffManagementControl.getStaffs(), BookManagementControl.getBooks());
    }

    public static List<ReturnCard> getReturnCards() {
        return returnCards;
    }

    public void add(ReturnCard returnCard) {
        returnCards.add(returnCard);
        FileReturnCardCSV.writeFile(returnCards);
    }

    public List<ReturnCard> findListReturnCardById(String inp) {
        List<ReturnCard> list = new ArrayList<>();
        for (ReturnCard returnCard : returnCards) {
            if (returnCard.getCallCard().getIdCallCard().equals(inp)) {
                list.add(returnCard);
            }
        }
        return list;
    }

    public ReturnCard findReturnCardByIdAndBook(CallCard callCard, Book book) {
        for (ReturnCard returnCard : returnCards) {
            if (returnCard.getBook().equals(book) && returnCard.getCallCard().equals(callCard)) {
                return returnCard;
            }
        }
        return null;
    }
}
