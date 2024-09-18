package com.example.myspringbootpractice.Service;

import com.example.myspringbootpractice.dto.User;

public interface UserService {

    User getUserById(int id);

    Integer createUser(User user);
}
