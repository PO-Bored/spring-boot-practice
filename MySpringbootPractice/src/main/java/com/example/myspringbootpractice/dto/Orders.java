package com.example.myspringbootpractice.dto;

import java.util.List;

public class Orders {
    private int userId;
    private List<OrderDetails> orderDetails;
    private double grandPrice;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public double getGrandPrice() {
        return grandPrice;
    }

    public void setGrandPrice(double grandPrice) {
        this.grandPrice = grandPrice;
    }
}
