package com.example.myspringbootpractice.dao;

import com.example.myspringbootpractice.dto.User;
import com.example.myspringbootpractice.dto.UserLogin;

public interface UserDao {

    User getUserById(int id);

    User getUserByEmail(String email);

    User getUserByAccount(String username);

    Integer checkPaAndEm(User userRequest);

    Integer createUser(User userRequest);

    User getUserByLogin(UserLogin userLogin);

}
