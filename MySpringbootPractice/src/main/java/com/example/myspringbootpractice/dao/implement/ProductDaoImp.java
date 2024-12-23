package com.example.myspringbootpractice.dao.implement;

import com.example.myspringbootpractice.dao.ProductDao;
import com.example.myspringbootpractice.dto.CartPro;
import com.example.myspringbootpractice.dto.OrderDetails;
import com.example.myspringbootpractice.dto.Orders;
import com.example.myspringbootpractice.dto.Product;
import com.example.myspringbootpractice.rowMapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Transactional
    @Override
    public int createOrder(Orders order) {
        String forOrdersSql = "INSERT INTO orders (user_id, grand_price) VALUES (:userId, :grandPrice)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        Map<String,Object> map = new HashMap<>();
        map.put("userId",order.getUserId());
        map.put("grandPrice",order.getGrandPrice());

        int rowsAffectedForOrders = namedParameterJdbcTemplate.update(forOrdersSql,new MapSqlParameterSource(map),keyHolder);
        if(rowsAffectedForOrders>0){
            int orderId = keyHolder.getKey().intValue();

            String forDetailSql = "INSERT INTO orderDetails (order_id, product_id, quantity, price) " +
                 "VALUES (:orderId, :productId, :quantity, :price)";
            MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[order.getOrderDetails().size()];
            for(int i = 0 ; i < order.getOrderDetails().size();i++){
                OrderDetails orderDetails = order.getOrderDetails().get(i);

                parameterSources[i] = new MapSqlParameterSource();
                parameterSources[i].addValue("orderId",orderId);
                parameterSources[i].addValue("productId",orderDetails.getProductId());
               parameterSources[i].addValue("quantity",orderDetails.getQuantity());
               parameterSources[i].addValue("price",orderDetails.getPrice());
            }
            int[] rowsAffectedForDetails = namedParameterJdbcTemplate.batchUpdate(forDetailSql,parameterSources);
            if(Arrays.stream(rowsAffectedForDetails).allMatch(count -> count > 0)){
                return 1;
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }
    @Transactional
    @Override
    public Integer deleteCart(Integer userId) {
        String sql = "DELETE FROM productsInCart WHERE userId=:userId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        int rowsAffected = namedParameterJdbcTemplate.update(sql, map);
        return rowsAffected;
    }
}
