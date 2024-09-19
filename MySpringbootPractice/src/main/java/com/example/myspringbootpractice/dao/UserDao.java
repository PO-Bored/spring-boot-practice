package com.example.myspringbootpractice.dao;

import com.example.myspringbootpractice.dto.User;

public interface UserDao {

    User getUserById(int id);

    User getUserByEmail(String email);

    Integer createUser(User userRequest);

}
