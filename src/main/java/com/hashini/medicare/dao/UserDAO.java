package com.hashini.medicare.dao;

import com.hashini.medicare.model.User;

public interface UserDAO {

    public User findByUsername(String username);

    void save(User user);
}