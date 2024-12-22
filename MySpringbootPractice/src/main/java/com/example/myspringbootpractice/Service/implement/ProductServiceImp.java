package com.example.myspringbootpractice.Service.implement;

import com.example.myspringbootpractice.Service.ProductService;
import com.example.myspringbootpractice.dao.ProductDao;
import com.example.myspringbootpractice.dto.CartPro;
import com.example.myspringbootpractice.dto.Orders;
import com.example.myspringbootpractice.dto.Product;
import com.example.myspringbootpractice.myException.productExceptionExtend.ProductNotfound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
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

    @Override
    public String addProductToCart(CartPro cartPro) {
        if(productDao.addProductToCart(cartPro)){
            return "成功加入購物車";
        }
        return "商品已在購物車";
    }


    @Override
    public List<Product> getUserProducts(int userId) {
        List<Product> products = productDao.getUserProduct(userId);
        return products;
    }

    @Override
    public int createOrder(Orders order) {
        int Dao = productDao.createOrder(order);
        if(Dao == 1){
            System.out.println("新增訂單資訊成功");
            return 1;
        }
        System.out.println("新增訂單資訊失敗");
            return 0;
    }
}
