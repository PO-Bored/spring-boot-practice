package com.example.myspringbootpractice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImp implements UserDao{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


}
