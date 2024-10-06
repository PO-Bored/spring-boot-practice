package com.example.myspringbootpractice.dao;

import com.example.myspringbootpractice.Enum.CheckResult;
import com.example.myspringbootpractice.dto.ResetPassword;
import com.example.myspringbootpractice.dto.User;
import com.example.myspringbootpractice.dto.UserLogin;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserDao {

    CheckResult checkAcAndEm(User userRequest);

    CheckResult checkAcAndPa(UserLogin userLogin);

    User getUserById(int id);

    User getUserByEmail(String email);

    User getUserByAc(UserLogin userLogin);

    Integer createUser(User userRequest);

    void resetPassword(ResetPassword resetPassword);

    void resetToken(String email, String token, LocalDateTime expireTime);

    User getByToken(String token);


}
