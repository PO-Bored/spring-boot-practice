package com.example.myspringbootpractice.Service;

import com.example.myspringbootpractice.dto.ResetPassword;
import com.example.myspringbootpractice.dto.User;
import com.example.myspringbootpractice.dto.UserLogin;

public interface UserService {

    User getUserById(int id);

    Integer register(User user);

    User login(UserLogin userLogin);

    User getUserByEmail(String email);

    void resetPassword(ResetPassword resetPassword);
}
