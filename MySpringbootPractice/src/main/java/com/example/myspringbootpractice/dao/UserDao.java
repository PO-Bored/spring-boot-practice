package com.example.myspringbootpractice.dao;

import com.example.myspringbootpractice.Enum.CheckResult;
import com.example.myspringbootpractice.dto.User;
import com.example.myspringbootpractice.dto.UserLogin;

public interface UserDao {

    User getUserById(int id);

    User getUserByEmail(String email);

    User getUserByAc(UserLogin userLogin);

    CheckResult checkAcAndEm(User userRequest);

    Integer createUser(User userRequest);

    CheckResult checkAcAndPa(UserLogin userLogin);

}
