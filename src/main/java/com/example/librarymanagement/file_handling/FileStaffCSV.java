package com.example.librarymanagement.file_handling;

import com.example.librarymanagement.control.StaffManagenment;
import com.example.librarymanagement.model.Staff;

import java.io.*;
import java.util.List;

public class FileStaffCSV {
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

    public static void readFile(StaffManagenment staffManagenment) {
        String line = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("E:\\CodeGym\\LibraryManagement\\src\\main\\java\\com\\example\\librarymanagement\\database\\StaffData.csv"))){
            bufferedReader.readLine();
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String[] staffArray = line.split(",");
                staffManagenment.add(new Staff(Integer.parseInt(staffArray[0]), staffArray[1], staffArray[2], staffArray[3], staffArray[4], staffArray[5], staffArray[6], staffArray[7]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
