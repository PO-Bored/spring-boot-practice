package com.example.myspringbootpractice.dao;

import com.example.myspringbootpractice.dto.OrderItems;
import com.example.myspringbootpractice.dto.Product;


import java.util.List;

public interface OrderDao {

    List<OrderItems> getOrders(Integer userId);
}
