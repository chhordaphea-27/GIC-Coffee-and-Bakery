package com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.OrderAndItemList;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.OrderItem;

public class OrderItemListManagement extends OrderItem {
    
    OrderItemManagement orderItemManagement = new OrderItemManagement();
    OrderManagement orderManagement = new OrderManagement();

    public void deleteAllDataOrderItemList(OrderAndItemList orderAndItemList) {
        for (OrderItem orderItem : orderAndItemList.getOrderItemList()) {
            orderItemManagement.deleteOrderItem(orderItem.getId());
        }
        orderManagement.deleteOrder(orderAndItemList.getOrder().getId());
    }

}
