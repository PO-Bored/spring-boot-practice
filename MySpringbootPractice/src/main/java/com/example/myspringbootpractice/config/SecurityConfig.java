//package com.example.myspringbootpractice.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // 如需測試可以暫時禁用 CSRF
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/users/login").permitAll() // 允許未經認證的訪問
//                        .anyRequest().authenticated()
//                );
//
//        return http.build();
//    }
//}
