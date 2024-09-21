package com.example.myspringbootpractice.Service.implement;

import com.example.myspringbootpractice.Enum.CheckResult;
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
        CheckResult checkResult = userDao.checkAcAndEm(userRequest);

        if(checkResult == CheckResult.ACCOUNT_EXISTS){   //檢查帳號是否已被使用
            log.warn("該帳號 {} 已被註冊",userRequest.getAccount());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "帳號已被使用");
        }if(checkResult == CheckResult.EMAIL_EXISTS){    //檢查email是否已被使用
            log.warn("該email {} 已被註冊", userRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email已被使用");
        }
        //密碼用Bcrypt加密
        String encodedPassword = passwordEncoder.hashPassword(userRequest.getPassword());
        userRequest.setPassword(encodedPassword);

        return userDao.createUser(userRequest);
    }

    @Override
    public User login(UserLogin userLogin) {
        User user = userDao.getUserByAc(userLogin);

        if(user == null){
            log.warn("該帳號不存在");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"帳號錯誤");
        } else if(passwordEncoder.matchPassword(userLogin.getPassword(),user.getPassword())){
            return user;
        }else{
            log.warn("密碼錯誤");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"密碼錯誤");
        }
    }
}
