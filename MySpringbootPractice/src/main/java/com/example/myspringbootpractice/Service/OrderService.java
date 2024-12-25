package com.example.myspringbootpractice.Service;

import com.example.myspringbootpractice.dto.OrderItems;
import com.example.myspringbootpractice.dto.Product;

import java.util.List;

public interface OrderService {

    List<OrderItems> getOrderItems(Integer userId);
}
