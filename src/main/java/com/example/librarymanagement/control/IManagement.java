package com.example.librarymanagement.control;

public interface IManagement<T> {
    void add(T t);
    void update(String id, T t);
    void delete(String id);
    int findIndexById(String id);
}
