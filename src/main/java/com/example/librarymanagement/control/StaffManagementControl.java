package com.example.librarymanagement.control;


import com.example.librarymanagement.file_handle.FileStaffCSV;
import com.example.librarymanagement.model.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffManagementControl implements IManagement<Staff> {
    private static final List<Staff> staffs = new ArrayList<>();
    private Staff currentStaff;

    public static List<Staff> getStaffs() {
        return staffs;
    }

    public Staff getCurrentStaff() {
        return currentStaff;
    }

    public void setCurrentStaff(Staff currentStaff) {
        this.currentStaff = currentStaff;
    }

    public StaffManagementControl() {
        FileStaffCSV.readFile(staffs);
    }

    @Override
    public void add(Staff staff) {
        staffs.add(staff);
        FileStaffCSV.writeFile(staffs);
    }

    @Override
    public void update(String id, Staff staff) {
        staffs.set(findIndexById(id), staff);
        FileStaffCSV.writeFile(staffs);
    }

    @Override
    public void delete(String id) {
        staffs.remove(findIndexById(id));
        FileStaffCSV.writeFile(staffs);
    }

    @Override
    public int findIndexById(String id) {
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getIdStaff() == Integer.parseInt(id)) {
                return i;
            }
        }
        return -1;
    }

    public List<Staff> findStaffByIdOrName(String inp) {
        List<Staff> listFind = new ArrayList<>();
        for (Staff staff : staffs) {
            if (String.valueOf(staff.getIdStaff()).contains(inp) || staff.getNameStaff().contains(inp)) {
                listFind.add(staff);
            }
        }
        return listFind;
    }

    public boolean Login(String username, String password) {
        boolean check;
        for (Staff staff : staffs) {
            if (staff.getUsername().equals(username) && staff.getPassword().equals(password)) {
                setCurrentStaff(staff);
                return check = true;
            }
        }
        return check = false;
    }
}
