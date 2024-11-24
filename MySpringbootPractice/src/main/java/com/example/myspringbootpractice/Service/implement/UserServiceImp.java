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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserServiceImp implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private PasswordService passwordEncoder = new PasswordService();

    @Autowired
    private JavaMailSender mailSender;

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
    public void forgetPassword(String email) {
        User user = getUserByEmail(email);
        String token = UUID.randomUUID().toString();
        userDao.resetToken(email,token, LocalDateTime.now().plusMinutes(30));

        String resetPasswordUrl = "http://localhost:8080/users/reset-password?token=" + token;

        //加了token的地址，之後要改寄到信箱的功能
        System.out.println(resetPasswordUrl);
        sendEmail(email,resetPasswordUrl);
    }

    @Override
    public void resetPassword(ResetPassword resetPassword) {

        String encodedPassword = passwordEncoder.hashPassword(resetPassword.getNewPassword());
        resetPassword.setNewPassword(encodedPassword);

        userDao.resetPassword(resetPassword);
    }

    @Override
    public boolean validateResetToken(String token){
        Optional<User> optionalUser = Optional.ofNullable(userDao.getByToken(token));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getTokenExpiry().isAfter(LocalDateTime.now())) {
                return true;  // Token 有效
            }
        }
        return false;  // Token 無效或已過期
    }

    public void sendEmail(String to, String resetLink){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("test1<zaqxswcde741963@gmail.com>");
        message.setTo(to);
        message.setSubject("密碼重新設置請求");
        message.setText("重新設置密碼請按此連結"+resetLink);
        mailSender.send(message);

    }
}
