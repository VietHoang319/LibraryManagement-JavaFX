package com.example.librarymanagement.control;

import com.example.librarymanagement.file_handling.FileReaderCSV;
import com.example.librarymanagement.model.Reader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReaderManagementControl implements IManagement<Reader>{
    private final static List<Reader> listReader = new ArrayList<>();

    public ReaderManagementControl() {
        FileReaderCSV.readFile(listReader);
        autoSetLock();
    }

    public static List<Reader> getListReader() {
        return listReader;
    }

    @Override
    public void add(Reader reader) {
        listReader.add(reader);
        FileReaderCSV.writeFile(listReader);
    }

    @Override
    public void update(String id, Reader reader) {
        listReader.set(findId(id), reader);
        FileReaderCSV.writeFile(listReader);
    }

    @Override
    public void delete(String id) {
        listReader.remove(findId(id));
        FileReaderCSV.writeFile(listReader);
    }

    @Override
    public int findId(String id) {
        for (int i = 0; i < listReader.size(); i++) {
            if (listReader.get(i).getIdReader().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public List<Reader> findReaderByIdOrName(String inp) {
        List<Reader> listFind = new ArrayList<>();
        for (Reader reader : listReader) {
            if (String.valueOf(reader.getIdReader()).contains(inp) || reader.getNameReader().contains(inp)) {
                listFind.add(reader);
            }
        }
        return listFind;
    }

    public void autoSetLock() {
        LocalDateTime localDateTime = LocalDateTime.now();
        for (Reader reader : listReader) {
            if(reader.getExpiry().isEqual(localDateTime) || !reader.getExpiry().isAfter(localDateTime)) {
                reader.setLock(true);
            }
        }
        FileReaderCSV.writeFile(listReader);
    }

    public void extendExpiry(String id, Reader reader) {
        listReader.set(findId(id), reader);
        FileReaderCSV.writeFile(listReader);
    }
}
