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
    public void update(String id, Staff staff) {

    }

    @Override
    public void delete(String id) {
          listStaff.remove(findId(id));
    }

    @Override
    public int findId(String id) {
        for (int i = 0; i < listStaff.size(); i++) {
            if (listStaff.get(i).getIdStaff() == Integer.parseInt(id)) {
                return i;
            }
        }
        return -1;
    }
}
