package com.example.myspringbootpractice.dao.implement;


import com.example.myspringbootpractice.Enum.CheckResult;
import com.example.myspringbootpractice.dao.UserDao;
import com.example.myspringbootpractice.dto.ResetPassword;
import com.example.myspringbootpractice.dto.User;
import com.example.myspringbootpractice.dto.UserLogin;
import com.example.myspringbootpractice.rowMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImp implements UserDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override //創建帳號時,確認帳號與信箱是否已被使用
    public CheckResult checkAcAndEm(User userRequest) {

        String sql = "select account, email from users where account=:account or email=:email";
        Map<String, Object> map = new HashMap<>();
        map.put("account", userRequest.getAccount());
        map.put("email", userRequest.getEmail());



        List<Map<String,Object>> results = namedParameterJdbcTemplate.queryForList(sql, map);

        for (Map<String,Object> result : results) {
            if(result.get("account").equals(userRequest.getAccount())){
                return CheckResult.ACCOUNT_EXISTS;
            }if(result.get("email").equals(userRequest.getEmail())){
                return CheckResult.EMAIL_EXISTS;
            }
        }
        return CheckResult.NONE;
    }

    @Override //登入帳號時,確認帳號與密碼是否符合
    public CheckResult checkAcAndPa(UserLogin userLogin) {
        String sql = "select * from users where account = :account or password = :password";
        List<User> user = namedParameterJdbcTemplate.query(sql,new BeanPropertySqlParameterSource(userLogin),new UserRowMapper());

        boolean accountExists = false;
        boolean passwordExists = false;

        if(user.size()>0){
            if(user.get(0).getAccount().equals(userLogin.getAccount())){
                accountExists = true;
            }if(user.get(0).getPassword().equals(userLogin.getPassword())){
                passwordExists = true;
            }
            if(accountExists && passwordExists){
                return CheckResult.PASS;
            }if(accountExists){
                return CheckResult.ACCOUNT_EXISTS;
            }if(passwordExists){
                return CheckResult.PASS;
            }
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {

        String sql = "select * from users where email = :eamil";
        Map<String, Object> map = new HashMap<>();
        map.put("eamil", email);
        List<User> user = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if (user.size() > 0) {
            return user.get(0);
        }else{
            return null;
        }
    }

    @Override
    public User getUserById(int id) {
        String sql = "select * from users where id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<User> user = namedParameterJdbcTemplate.query(sql,params,new UserRowMapper());

        if(user.size()>0){
            return user.get(0);
        }else{
            return null;
        }

    }

    @Override
    public User getByToken(String token) {
        String sql = "select * from users where reset_token = :token";
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);
        List<User> user = namedParameterJdbcTemplate.query(sql,params,new UserRowMapper());
        if(user.size()>0){
            return user.get(0);
        }else{
            return null;
        }
    }

    @Override
    public User getUserByAc(UserLogin userLogin) {
        String sql = "select * from users where account = :account";
        List<User> user = namedParameterJdbcTemplate.query(sql,new BeanPropertySqlParameterSource(userLogin),new UserRowMapper());

        if(user.size()>0){
            return user.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer createUser(User userRequest) {

        String sql = "Insert into users(name,account,password,phone,email) " +
                "values(:name,:account,:password,:phone,:email)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(userRequest), keyHolder);
        int id = keyHolder.getKey().intValue();
        return id;
    }

    @Override
    public void resetPassword(ResetPassword resetPassword) {
        String sql = "UPDATE users SET password = :newPassword,reset_token = NULL, token_expiry = NULL WHERE email=:email";
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(resetPassword));
    }

    @Override
    public void resetToken(String email, String token, LocalDateTime expireTime) {
        String sql = "UPDATE users SET reset_token = :token ," +
                "token_expiry = :expireTime WHERE email=:email";
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("token", token);
        params.put("expireTime", expireTime);
        namedParameterJdbcTemplate.update(sql, params);

    }
}
