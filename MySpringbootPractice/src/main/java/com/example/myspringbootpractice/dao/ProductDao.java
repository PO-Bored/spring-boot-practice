package com.example.myspringbootpractice.dao;

import com.example.myspringbootpractice.dto.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProduct();
}
