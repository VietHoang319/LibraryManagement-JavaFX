package com.example.librarymanagement.control;

import com.example.librarymanagement.model.CallCard;

import java.util.ArrayList;
import java.util.List;

public class CallCardManagement {
    private static final List<CallCard> listCallCard = new ArrayList<>();

    public static List<CallCard> getReturnCards() {
        return listCallCard;
    }

    public void addCallCard(CallCard callCard) {
        listCallCard.add(callCard);
    }
}
