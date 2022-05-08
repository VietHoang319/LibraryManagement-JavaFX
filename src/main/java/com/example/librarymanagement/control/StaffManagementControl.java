package com.example.librarymanagement.control;


import com.example.librarymanagement.file_handling.FileStaffCSV;
import com.example.librarymanagement.model.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffManagementControl implements IManagement<Staff> {
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

    public StaffManagementControl() {
        FileStaffCSV.readFile(listStaff);
    }

    @Override
    public void add(Staff staff) {
        listStaff.add(staff);
        FileStaffCSV.writeFile(listStaff);
    }

    @Override
    public void update(String id, Staff staff) {
        listStaff.set(findIndexById(id), staff);
        FileStaffCSV.writeFile(listStaff);
    }

    @Override
    public void delete(String id) {
        listStaff.remove(findIndexById(id));
        FileStaffCSV.writeFile(listStaff);
    }

    @Override
    public int findIndexById(String id) {
        for (int i = 0; i < listStaff.size(); i++) {
            if (listStaff.get(i).getIdStaff() == Integer.parseInt(id)) {
                return i;
            }
        }
        return -1;
    }

    public List<Staff> findStaffByIdOrName(String inp) {
        List<Staff> listFind = new ArrayList<>();
        for (Staff staff : listStaff) {
            if (String.valueOf(staff.getIdStaff()).contains(inp) || staff.getNameStaff().contains(inp)) {
                listFind.add(staff);
            }
        }
        return listFind;
    }

    public boolean Login(String username, String password) {
        boolean check;
        for (Staff staff : listStaff) {
            if (staff.getUsername().equals(username) && staff.getPassword().equals(password)) {
                setCurrentStaff(staff);
                return check = true;
            }
        }
        return check = false;
    }
}
