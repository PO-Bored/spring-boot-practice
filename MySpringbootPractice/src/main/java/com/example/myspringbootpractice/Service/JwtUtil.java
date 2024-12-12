package com.example.myspringbootpractice.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;



public class JwtUtil {
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //生成Token
    public static String generateToken(int userId, String userAccount) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))        // 用戶 ID 作為主體
                .claim("userAccount", userAccount)              // 添加用戶名作為自定義數據
                .setIssuedAt(new Date())                  // 簽發時間
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1小時有效期
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 簽名
                .compact();
    }

    //驗證token並提取claim
    public static Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean isToken(String token){
        try {
            Claims claim = validateToken(token);
            return claim != null;
        }catch(Exception e){
            return false;
        }
    }
    public static void main(String[] args) {
        int userId =5;
        String username = "Andy";
        String token = generateToken(userId, username);
        System.out.println(token);
        System.out.println(isToken(token));
    }
}
