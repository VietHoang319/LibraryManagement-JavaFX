package com.example.librarymanagement.model;


import com.example.librarymanagement.control.StaffManagementControl;

public class Staff {
    private int idStaff;
    private String nameStaff;
    private String addressStaff;
    private String emailStaff;
    private String phoneNumberStaff;
    private String username;
    private String password;
    private String role;

    public Staff() {
    }

    public Staff(String nameStaff, String addressStaff, String emailStaff, String phoneNumberStaff, String username, String password, String role) {
        int sizeOfListEqualZero = 0;
        if (StaffManagementControl.getStaffs().size() == sizeOfListEqualZero) {
            this.idStaff = 0;
        } else {
            this.idStaff = StaffManagementControl.getStaffs().get(StaffManagementControl.getStaffs().size() - 1).getIdStaff() + 1;
        }
        this.nameStaff = nameStaff;
        this.addressStaff = addressStaff;
        this.emailStaff = emailStaff;
        this.phoneNumberStaff = phoneNumberStaff;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Staff(int idStaff, String nameStaff, String addressStaff, String emailStaff, String phoneNumberStaff, String username, String password, String role) {
        this.idStaff = idStaff;
        this.nameStaff = nameStaff;
        this.addressStaff = addressStaff;
        this.emailStaff = emailStaff;
        this.phoneNumberStaff = phoneNumberStaff;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getIdStaff() {
        return idStaff;
    }

    public String getNameStaff() {
        return nameStaff;
    }

    public void setNameStaff(String nameStaff) {
        this.nameStaff = nameStaff;
    }

    public String getAddressStaff() {
        return addressStaff;
    }

    public void setAddressStaff(String addressStaff) {
        this.addressStaff = addressStaff;
    }

    public String getEmailStaff() {
        return emailStaff;
    }

    public void setEmailStaff(String emailStaff) {
        this.emailStaff = emailStaff;
    }

    public String getPhoneNumberStaff() {
        return phoneNumberStaff;
    }

    public void setPhoneNumberStaff(String phoneNumberStaff) {
        this.phoneNumberStaff = phoneNumberStaff;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
