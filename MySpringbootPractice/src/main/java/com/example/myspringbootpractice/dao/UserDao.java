package com.example.myspringbootpractice.dao;

import com.example.myspringbootpractice.dto.User;

public interface UserDao {

    User getUserById(int id);

    Integer createUser(User user);

}
