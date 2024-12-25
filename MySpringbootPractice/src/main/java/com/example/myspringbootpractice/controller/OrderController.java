package com.example.myspringbootpractice.controller;

import com.example.myspringbootpractice.Service.OrderService;
import com.example.myspringbootpractice.dto.OrderItems;
import com.example.myspringbootpractice.dto.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/getOrders")
    public ResponseEntity<List<OrderItems>> getOrders(@RequestBody UserId userId) {
        System.out.println("抵達方法");
        List<OrderItems> orderItems = orderService.getOrderItems(userId.getUserId());

        return ResponseEntity.ok(orderItems);
    }
}
