package com.example.demo.dao;

import java.util.List;

public interface MyDAO {
    List<Object> fetchAll();
    Object fetchById(int theId);
    void save(Object object);
    void deleteById(int theId);
}
