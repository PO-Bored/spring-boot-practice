package com.example.myspringbootpractice.dao;

import com.example.myspringbootpractice.dto.CartPro;
import com.example.myspringbootpractice.dto.Orders;
import com.example.myspringbootpractice.dto.Product;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProduct();

    Product getProductById(int productId);

    boolean addProductToCart(CartPro cartPro);

    List<Product> getUserProduct(int userId);

    int createOrder(Orders order);

    Integer deleteCart(Integer userId);

    Integer deleteProductInCart(CartPro cartPro);
}
