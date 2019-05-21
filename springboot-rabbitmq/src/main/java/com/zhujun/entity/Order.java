package com.zhujun.entity;

import lombok.*;

import java.io.Serializable;

/**
 * className: Order
 * create by: zhujun
 * description: 发送到消息队列的订单对象
 * create time: 2019/5/21 10:53
 */

public class Order implements Serializable {
    private String orderId;
    private String orderStatus;//订单状态
    private String payStatus;//支付状态


    public Order(String orderId, String orderStatus, String payStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.payStatus = payStatus;
    }

    public Order() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", payStatus='" + payStatus + '\'' +
                '}';
    }
}
