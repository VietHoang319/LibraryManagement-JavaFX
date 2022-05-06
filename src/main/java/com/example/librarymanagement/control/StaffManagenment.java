package com.example.librarymanagement.control;


import com.example.librarymanagement.file_handling.FileStaffCSV;
import com.example.librarymanagement.model.Staff;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class StaffManagenment implements IManagement<Staff>{
    private static final List<Staff> listStaff = new ArrayList<>();
    private Staff currentStaff;

    public static List<Staff> getListStaff() {
        return listStaff;
    }

    public Staff getCurrentStaff() {
        return currentStaff;
    }

    public void setCurrentStaff(Staff currentStaff) {
        this.currentStaff = currentStaff;
    }

    static {
        listStaff.add(new Staff(1, "Ngô Việt Hoàng", "", "", "", "", "", ""));
    }

    @Override
    public void add(Staff staff) {
        listStaff.add(staff);
        FileStaffCSV.writeFile(listStaff);
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void findId() {

    }

//    @Override
//    public Array show() {
//        Staff[] list = new Staff[listStaff.size()];
//        for (Staff staff : listStaff) {
//            list.pus
//        }
//        return ;
//    }

}
