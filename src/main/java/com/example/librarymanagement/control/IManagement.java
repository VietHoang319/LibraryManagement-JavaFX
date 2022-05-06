package com.example.librarymanagement.control;

import java.lang.reflect.Array;

public interface IManagement<T> {
    void add(T t);
    void update();
    void delete();
    void findId();
//    Array show();
}
