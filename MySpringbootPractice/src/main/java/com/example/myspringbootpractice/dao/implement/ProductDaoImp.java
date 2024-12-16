package com.example.myspringbootpractice.dao.implement;

import com.example.myspringbootpractice.dao.ProductDao;
import com.example.myspringbootpractice.dto.CartPro;
import com.example.myspringbootpractice.dto.Product;
import com.example.myspringbootpractice.rowMapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    @Override
    public List<Product> getUserProduct(int userId) {
        String sql = "SELECT productId FROM productsincart WHERE userId=?;";
        List<Integer> productIds = jdbcTemplate.queryForList(sql,Integer.class,userId);
        if(productIds==null){
            return null;
        }else{
            for(int id:productIds){
                System.out.println(id);
            }
        }
        String sql2 = "SELECT  productid, name, description, price, stock," +
                "img_url FROM product WHERE productId IN (:productIds)";
        Map<String, Object> map = new HashMap<>();
        map.put("productIds",productIds);
        List<Product> products = namedParameterJdbcTemplate.query(sql2,map,new ProductRowMapper());
        return products;
    }
}
