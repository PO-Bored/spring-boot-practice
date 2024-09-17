package com.example.myspringbootpractice.dao.implement;


import com.example.myspringbootpractice.dao.UserDao;
import com.example.myspringbootpractice.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserDaoImp implements UserDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Integer createUser(User user) {

        String sql = "Insert into user(name,account,password,phone,email) " +
                "values(:name,:account,:password,:phone,:email)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(user), keyHolder);
        int id = keyHolder.getKey().intValue();
        return id;
    }
}
