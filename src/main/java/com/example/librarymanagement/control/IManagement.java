package com.example.librarymanagement.control;

import java.lang.reflect.Array;

public interface IManagement<T> {
    void add(T t);
    void update(String id, T t);
    void delete(String id);
    int findId(String id);
}
