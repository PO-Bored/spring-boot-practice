package com.example.myspringbootpractice.rowMapper;

import com.example.myspringbootpractice.dto.OrderItems;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemsRowMapper implements RowMapper<OrderItems> {


    @Override
    public OrderItems mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderId(rs.getInt("order_id"));
        orderItems.setGrandPrice(rs.getInt("grand_price"));
        orderItems.setProductId(rs.getInt("productid"));
        orderItems.setImgUrl(rs.getString("img_url"));
        orderItems.setProductName(rs.getString("product_name"));
        orderItems.setPrice(rs.getInt("order_price"));
        orderItems.setQuantity(rs.getInt("quantity"));
        return orderItems;
    }
}
