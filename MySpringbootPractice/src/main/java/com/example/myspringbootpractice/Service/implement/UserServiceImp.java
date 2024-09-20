package com.example.myspringbootpractice.Service.implement;

import com.example.myspringbootpractice.Service.UserService;
import com.example.myspringbootpractice.dao.UserDao;
import com.example.myspringbootpractice.dto.User;
import com.example.myspringbootpractice.dto.UserLogin;
import com.example.myspringbootpractice.hash.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImp implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private PasswordService passwordEncoder = new PasswordService();

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public Integer register(User userRequest) {
        User user = userDao.getUserByEmail(userRequest.getEmail());
        if(user != null){
            log.warn("該eamil {} 已被註冊", userRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        user = userDao.getUserByAccount(userRequest.getAccount());

        String encodedPassword = passwordEncoder.hashPassword(userRequest.getPassword());
        userRequest.setPassword(encodedPassword);


        return userDao.createUser(userRequest);
    }

    @Override
    public User login(UserLogin userLogin) {



        return userDao.getUserByLogin(userLogin);
    }
}
