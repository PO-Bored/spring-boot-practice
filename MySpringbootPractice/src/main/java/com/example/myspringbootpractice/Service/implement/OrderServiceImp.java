package com.example.myspringbootpractice.Service.implement;

import com.example.myspringbootpractice.Service.OrderService;
import com.example.myspringbootpractice.dto.Product;

import java.util.List;

public class OrderServiceImp implements OrderService {
    @Override
    public List<Product> getOrders(Integer userId) {
        return List.of();
    }
}
