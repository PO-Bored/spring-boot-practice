package com.example.myspringbootpractice.rowMapper;

import com.example.myspringbootpractice.dto.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {


    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setName(rs.getString("name"));
        user.setAccount(rs.getString("account"));
        user.setPassword(rs.getString("password"));
        user.setPhone(rs.getInt("phone"));
        user.setEmail(rs.getString("email"));

        return user;
    }
}
