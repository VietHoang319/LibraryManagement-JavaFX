package com.example.librarymanagement.file_handling;

import com.example.librarymanagement.control.StaffManagenment;
import com.example.librarymanagement.model.Staff;

import java.io.*;
import java.util.List;

public class FileStaffCSV {

    public static final String NULLVALUE = "";

    public static void writeFile(List<Staff> list) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("E:\\CodeGym\\LibraryManagement\\src\\main\\java\\com\\example\\librarymanagement\\database\\StaffData.csv"))) {
            bufferedWriter.write("id, Name, Address, Email, Phone number, Username, Password, Role");
            bufferedWriter.newLine();
            for (Staff staff: list) {
                bufferedWriter.write(staff.getIdStaff() + "," + staff.getNameStaff() + "," + staff.getAddressStaff() + ","
                        + staff.getEmailStaff() + "," + staff.getPhoneNumberStaff() + "," + staff.getUsername() + "," + staff.getPassword() + "," + staff.getRole());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFile(List<Staff> list) {
        String line = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("E:\\CodeGym\\LibraryManagement\\src\\main\\java\\com\\example\\librarymanagement\\database\\StaffData.csv"))){
            bufferedReader.readLine();
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String[] staffArray = line.split(",");
                int id = Integer.parseInt(staffArray[0]);
                String name = staffArray[1];
                String address;
                if (staffArray[2].equals(NULLVALUE)) {
                    address = NULLVALUE;
                } else {
                    address = staffArray[2];
                }
                String email;
                if (staffArray[3].equals(NULLVALUE)) {
                    email = NULLVALUE;
                } else {
                    email = staffArray[3];
                }
                String phoneNumber;
                if (staffArray[4].equals(NULLVALUE)) {
                    phoneNumber = NULLVALUE;
                } else {
                    phoneNumber = staffArray[4];
                }
                String username = staffArray[5];
                String password = staffArray[6];
                String role = staffArray[7];
                list.add(new Staff(id, name, address, email, phoneNumber, username, password, role));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
