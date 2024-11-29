package com.example.myspringbootpractice.myException.productExceptionExtend;

import com.example.myspringbootpractice.myException.ProductException;

public class ProductNotfound extends ProductException {

    public ProductNotfound() {
        super(404, "查無此商品");
    }
}
