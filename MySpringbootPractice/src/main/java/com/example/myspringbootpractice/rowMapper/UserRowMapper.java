package com.example.myspringbootpractice.rowMapper;

import com.example.myspringbootpractice.dto.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {


    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setAccount(rs.getString("account"));
        user.setPassword(rs.getString("password"));
        user.setPhone(rs.getString("phone"));
        user.setEmail(rs.getString("email"));
        //user.getToken(rs.getString("reset_token"));
        // 從資料庫中的 TIMESTAMP 字段提取並轉換為 LocalDateTime
//        Timestamp tokenExpiryTimestamp = rs.getTimestamp("token_expiry");
//        if (tokenExpiryTimestamp != null) {
//            user.setTokenExpiry(tokenExpiryTimestamp.toLocalDateTime());
//        }

        return user;
    }
}
