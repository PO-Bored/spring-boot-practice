package com.example.myspringbootpractice.Service.implement;

import com.example.myspringbootpractice.Service.ProductService;
import com.example.myspringbootpractice.dao.ProductDao;
import com.example.myspringbootpractice.dto.Product;
import com.example.myspringbootpractice.myException.productExceptionExtend.ProductNotfound;
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

    @Override
    public Product getProductById(int id) {
        Product product = productDao.getProductById(id);
        if(product == null){
            throw new ProductNotfound();
        }
        return product;
    }


}
