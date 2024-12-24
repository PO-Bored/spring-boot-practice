package com.example.myspringbootpractice.controller;

import com.example.myspringbootpractice.dto.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private

    @RequestMapping("/getOrders")
    public ResponseEntity<List<Product>> getOrders(@RequestBody Integer userId) {

    }
}
