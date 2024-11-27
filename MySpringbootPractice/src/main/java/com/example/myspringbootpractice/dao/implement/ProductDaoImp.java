package com.example.myspringbootpractice.dao.implement;

import com.example.myspringbootpractice.dao.ProductDao;
import com.example.myspringbootpractice.dto.Product;
import com.example.myspringbootpractice.rowMapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDaoImp implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getAllProduct() {
        String sql = "SELECT  productid, name, description, price, stock," +
                "img_url FROM mycart.product ";
        List<Product> products = namedParameterJdbcTemplate.query(sql, new ProductRowMapper());
        return products;
    }
}
