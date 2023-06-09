package com.gic_coffee_and_bakery.softwareeginerringgroup13.Model;

import java.util.List;

public class OrderAndItemList extends Model {
    private Order order;
    private List<OrderItem> orderItemList;


    public OrderAndItemList() {
    }

    public OrderAndItemList(Order order, List<OrderItem> orderItem) {
        this.order = order;
        this.orderItemList = orderItem;
    }
    
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }
    public void setOrderItemList(List<OrderItem> orderItem) {
        this.orderItemList = orderItem;
    }
    public void addOrderItemList(OrderItem orderItem) {
        this.orderItemList.add(orderItem);
    }






}
