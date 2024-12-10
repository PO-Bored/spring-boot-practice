package com.example.myspringbootpractice.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;



public class JwtUtil {
    private static final String SECRET = "secret";

    //生成Token
    public static String generateToken(int userId, String username) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))        // 用戶 ID 作為主體
                .claim("username", username)              // 添加用戶名作為自定義數據
                .setIssuedAt(new Date())                  // 簽發時間
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1小時有效期
                .signWith(SignatureAlgorithm.HS256, SECRET) // 簽名
                .compact();
    }

    //驗證token並提取claim
    public static Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
