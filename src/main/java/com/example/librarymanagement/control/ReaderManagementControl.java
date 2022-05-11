package com.example.librarymanagement.control;

import com.example.librarymanagement.file_handle.FileReaderCSV;
import com.example.librarymanagement.model.CallCardInfor;
import com.example.librarymanagement.model.Reader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReaderManagementControl implements IManagement<Reader>{
    private final static List<Reader> readers = new ArrayList<>();

    public ReaderManagementControl() {
        FileReaderCSV.readFile(readers);
        autoSetLock();
    }

    public static List<Reader> getReaders() {
        return readers;
    }

    @Override
    public void add(Reader reader) {
        readers.add(reader);
        FileReaderCSV.writeFile(readers);
    }

    @Override
    public void update(String id, Reader reader) {
        readers.set(findIndexById(id), reader);
        FileReaderCSV.writeFile(readers);
    }

    @Override
    public void delete(String id) {
        readers.remove(findIndexById(id));
        FileReaderCSV.writeFile(readers);
    }

    @Override
    public int findIndexById(String id) {
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i).getIdReader().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public List<Reader> findReaderByIdOrName(String inp) {
        List<Reader> listFind = new ArrayList<>();
        for (Reader reader : readers) {
            if (String.valueOf(reader.getIdReader()).contains(inp) || reader.getNameReader().contains(inp)) {
                listFind.add(reader);
            }
        }
        return listFind;
    }

    public static void autoSetLock() {
        LocalDateTime localDateTime = LocalDateTime.now();
        for (Reader reader : readers) {
            if(!reader.getExpiry().isAfter(localDateTime)) {
                reader.setLock(true);
            }
        }
        for (CallCardInfor callCardInfor : CallCardInformationManagementControl.getCallCardInfors()) {
            if (!callCardInfor.getReturnDeadline().isAfter(localDateTime)) {
                callCardInfor.getCallCard().getReader().setLock(true);
            }
        }
        FileReaderCSV.writeFile(readers);
    }

    public void extendExpiry(String id, Reader reader) {
        readers.set(findIndexById(id), reader);
        FileReaderCSV.writeFile(readers);
    }

    public List<String> getListId() {
        List<String> list = new ArrayList<>();
        for (Reader reader: readers) {
            list.add(reader.getIdReader());
        }
        return list;
    }
}
