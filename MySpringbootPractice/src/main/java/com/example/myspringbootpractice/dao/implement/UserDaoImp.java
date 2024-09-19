package com.example.myspringbootpractice.dao.implement;


import com.example.myspringbootpractice.dao.UserDao;
import com.example.myspringbootpractice.dto.User;
import com.example.myspringbootpractice.rowMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImp implements UserDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
    public Integer createUser(User userRequest) {

        String sql = "Insert into users(name,account,password,phone,email) " +
                "values(:name,:account,:password,:phone,:email)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(userRequest), keyHolder);
        int id = keyHolder.getKey().intValue();
        return id;
    }
}
