package com.example.myspringbootpractice.dao.implement;

import com.example.myspringbootpractice.dao.ProductDao;
import com.example.myspringbootpractice.dto.CartPro;
import com.example.myspringbootpractice.dto.Product;
import com.example.myspringbootpractice.rowMapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Product getProductById(int productId) {
        String sql = "SELECT  productid, name, description, price, stock," +
                "img_url FROM mycart.product WHERE productid=:productid";

        Map<String, Object> map = new HashMap<>();
        map.put("productid", productId);
        List<Product> products = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if (products != null && products.size() > 0) {
            return products.get(0);
        }
        return null;
    }

    @Override
    public boolean addProductToCart(CartPro cartPro) {

        String sql = "INSERT IGNORE INTO productsInCart (userId, productId) \n" +
                "VALUES (:userId, :productId);";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", cartPro.getUserId());
        map.put("productId", cartPro.getProductId());
        int rowsAffected = namedParameterJdbcTemplate.update(sql, map);
        return rowsAffected > 0;
    }
}
