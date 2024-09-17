package com.example.myspringbootpractice.Service.implement;

import com.example.myspringbootpractice.Service.UserService;
import com.example.myspringbootpractice.dao.UserDao;
import com.example.myspringbootpractice.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer createUser(User user) {
        return userDao.createUser(user);
    }
}
