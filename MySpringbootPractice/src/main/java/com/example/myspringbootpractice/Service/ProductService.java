package com.example.myspringbootpractice.Service;

import com.example.myspringbootpractice.dto.CartPro;
import com.example.myspringbootpractice.dto.Orders;
import com.example.myspringbootpractice.dto.Product;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(int id);

    String addProductToCart(CartPro cartPro);

    List<Product> getUserProducts(int userId);

    int createOrder(Orders order);

    void deleteCart(Integer userId);
}
