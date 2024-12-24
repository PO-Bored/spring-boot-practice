package com.example.myspringbootpractice.dao.implement;

import com.example.myspringbootpractice.dao.OrderDao;
import com.example.myspringbootpractice.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class OrderDaoImp implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //TODO
    @Override
    public List<Product> getOrders(Integer userId) {
        String sql = "SELECT "

        return List.of();
    }
}
