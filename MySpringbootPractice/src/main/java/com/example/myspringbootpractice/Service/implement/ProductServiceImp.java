package com.example.myspringbootpractice.Service.implement;

import com.example.myspringbootpractice.Service.ProductService;
import com.example.myspringbootpractice.dao.ProductDao;
import com.example.myspringbootpractice.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getAllProducts() {

        return productDao.getAllProduct();
    }
}
