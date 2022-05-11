package com.example.librarymanagement.control;

import com.example.librarymanagement.control.*;

public class ThreadHandle {
    private static final StaffManagementControl staffManagementControl = new StaffManagementControl();
    private static final ReaderManagementControl readerManagementControl = new ReaderManagementControl();
    private static final BookManagementControl bookManagementControl = new BookManagementControl();
    private static final CallCardManagementControl callCardManagementControl = new CallCardManagementControl();
    private static final CallCardInformationManagementControl callCardInformationManagementControl = new CallCardInformationManagementControl();
    private static final ReturnCardManagementControl returnCardManagementControl = new ReturnCardManagementControl();

    public static StaffManagementControl getStaffManagementControl() {
        return staffManagementControl;
    }

    public static ReaderManagementControl getReaderManagementControl() {
        return readerManagementControl;
    }

    public static BookManagementControl getBookManagementControl() {
        return bookManagementControl;
    }

    public static CallCardManagementControl getCallCardManagementControl() {
        return callCardManagementControl;
    }

    public static CallCardInformationManagementControl getCallCardInformationManagementControl() {
        return callCardInformationManagementControl;
    }

    public static ReturnCardManagementControl getReturnCardManagementControl() {
        return returnCardManagementControl;
    }
}
