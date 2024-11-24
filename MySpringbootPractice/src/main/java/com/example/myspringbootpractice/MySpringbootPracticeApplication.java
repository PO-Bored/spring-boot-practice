package com.example.myspringbootpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, SecurityAutoConfiguration.class})
public class MySpringbootPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringbootPracticeApplication.class, args);
    }

}
