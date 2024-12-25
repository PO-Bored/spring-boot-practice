package com.example.myspringbootpractice.dao.implement;

import com.example.myspringbootpractice.dao.OrderDao;

import com.example.myspringbootpractice.dto.OrderItems;

import com.example.myspringbootpractice.rowMapper.OrderItemsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImp implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    //TODO
    @Override
    public List<OrderItems> getOrders(Integer userId) {

        String sqlForDetails = "SELECT o.order_id,o.grand_price,p.productid," +
                " p.img_url,p.name AS product_name,od.price AS order_price," +
                " od.quantity FROM mycart.orders o" +
                " JOIN " +
                "     mycart.orderdetails od ON o.order_id = od.order_id" +
                " JOIN " +
                "     mycart.product p ON od.product_id = p.productid" +
                " WHERE " +
                "     o.user_id = :userId;";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        List<OrderItems> orderDetails = namedParameterJdbcTemplate.query(sqlForDetails, map, new OrderItemsRowMapper());
        System.out.println(orderDetails);
        return orderDetails;
    }
}
