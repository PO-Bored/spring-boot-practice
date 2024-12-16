package com.example.myspringbootpractice.controller;

import com.example.myspringbootpractice.Service.ProductService;
import com.example.myspringbootpractice.dto.CartPro;
import com.example.myspringbootpractice.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
//@CrossOrigin 跨域請求
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductService productService;

    @GetMapping("/AllProduct")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {

        return ResponseEntity.ok(productService.getProductById(productId));
    }
    @PostMapping("AddToCart")
    public ResponseEntity addToCart(@RequestBody CartPro cartPro) {
        String response = productService.addProductToCart(cartPro);
        return ResponseEntity.ok(response);
    }

    public int[] cartProducts()
}
