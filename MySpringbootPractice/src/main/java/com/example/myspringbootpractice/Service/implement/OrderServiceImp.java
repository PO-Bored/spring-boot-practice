package com.example.myspringbootpractice.Service.implement;

import com.example.myspringbootpractice.Service.OrderService;
import com.example.myspringbootpractice.dao.OrderDao;
import com.example.myspringbootpractice.dto.OrderItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<OrderItems> getOrderItems(Integer userId) {
        return orderDao.getOrders(userId);
    }
}
