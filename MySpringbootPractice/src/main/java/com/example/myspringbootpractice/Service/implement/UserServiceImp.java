package com.example.myspringbootpractice.Service.implement;

import com.example.myspringbootpractice.Enum.CheckResult;
import com.example.myspringbootpractice.Service.UserService;
import com.example.myspringbootpractice.dao.UserDao;
import com.example.myspringbootpractice.dto.ResetPassword;
import com.example.myspringbootpractice.dto.User;
import com.example.myspringbootpractice.dto.UserLogin;
import com.example.myspringbootpractice.hash.PasswordService;
import com.example.myspringbootpractice.myException.registerExceptionExtend.AccountException;
import com.example.myspringbootpractice.myException.registerExceptionExtend.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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
    public User getUserByEmail(String email) {

        if (userDao.getUserByEmail(email) != null) {
            return userDao.getUserByEmail(email);
        }else {
            log.warn("該信箱 {} 無效",email);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "無效的信箱");
        }
    }

    @Override
    public Integer register(User userRequest) {
        CheckResult checkResult = userDao.checkAcAndEm(userRequest);

        if(checkResult == CheckResult.ACCOUNT_EXISTS){   //檢查帳號是否已被使用
            log.warn("該帳號 {} 已被註冊",userRequest.getAccount());
            throw new AccountException();
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"帳號已被使用，請重新確認");
        }if(checkResult == CheckResult.EMAIL_EXISTS){    //檢查email是否已被使用
            log.warn("該email {} 已被註冊", userRequest.getEmail());
            throw new EmailException();
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"信箱已被使用，請重新確認");
        }
        //密碼用Bcrypt加密
        String encodedPassword = passwordEncoder.hashPassword(userRequest.getPassword());
        userRequest.setPassword(encodedPassword);

        return userDao.createUser(userRequest);
    }

    @Override
    public User login(UserLogin userLogin) {
        User user = userDao.getUserByAc(userLogin);
        if(user!=null) {
            if (passwordEncoder.matchPassword(userLogin.getPassword(), user.getPassword())) {
                return user;
            } else {
                log.warn("帳號或密碼錯誤");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "帳號或密碼錯誤");
            }
        }
        log.warn("帳號或密碼錯誤");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "帳號或密碼錯誤");
    }

    @Override
    public void resetPassword(ResetPassword resetPassword) {
        userDao.resetPassword(resetPassword);
    }
}
