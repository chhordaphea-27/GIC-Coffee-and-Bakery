package com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderItemManagement extends Management<OrderItem> {

    @Override
    protected OrderItem mapRowToModel(ResultSet rs) throws SQLException {
        int orderItemId = rs.getInt("order_item_id");
        int orderId = rs.getInt("order_id");
        int productSizeId = rs.getInt("product_size_id");
        int quantity = rs.getInt("quantity");
        boolean cream = rs.getBoolean("cream");
        String sugar = rs.getString("sugar");
        String note = rs.getString("note");

        OrderManagement orderManagement = new OrderManagement();
        Order order = orderManagement.getOrderById(orderId);

        ProductSizeManagement productSizeManagement = new ProductSizeManagement();
        ProductSize productSize = productSizeManagement.getProductSizeById(productSizeId);

        return new OrderItem(orderItemId, order, productSize, quantity, cream, sugar, note);
    }

    @Override
    protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, OrderItem orderItem) throws SQLException {
        stmt.setInt(1, orderItem.getOrder().getId());
        stmt.setInt(2, orderItem.getProductSize().getId());
        stmt.setInt(3, orderItem.getQuantity());
        stmt.setBoolean(4, orderItem.getCream());
        stmt.setString(5, orderItem.getSugar());
        stmt.setString(6, orderItem.getNote());

        if (!isAddOperation) {
            stmt.setInt(7, orderItem.getId());
        }
    }

    public int addOrderItem(OrderItem orderItem) {
        String query = "INSERT INTO order_item (order_id, product_size_id, quantity, cream, sugar, note) VALUES (?, ?, ?, ?, ?, ?)";
        return add(orderItem, query);
    }

    public void updateOrderItem(OrderItem orderItem) {
        String query = "UPDATE order_item SET order_id=?, product_size_id=?, quantity=?, cream=?, sugar=?, note=? WHERE order_item_id=?";
        update(orderItem, query);
    }

    public void deleteOrderItem(int id) {
        String query = "DELETE FROM order_item WHERE order_item_id=?";
        delete(id, query);
    }

    public List<OrderItem> getAllOrderItems() {
        String query = "SELECT * FROM order_item";
        return getAll(query);
    }

    public OrderItem getOrderItemById(int id) {
        String query = "SELECT * FROM order_item WHERE order_item_id=?";
        return getById(id, query);
    }

    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        String query = "SELECT * FROM order_item WHERE order_id=?";
        return query(String.valueOf(orderId), query);
    }


    // Implement methods to fetch Order and ProductSize by their IDs from the database
    // private Order getOrderById(int orderId) {
    //     OrderManagement orderManagement = new OrderManagement();
    //     return orderManagement.getOrderById(orderId);
    // }
    
    // private ProductSize getProductSizeById(int productSizeId) {
    //     ProductSizeManagement productSizeManagement = new ProductSizeManagement();
    //     return productSizeManagement.getProductSizeById(productSizeId);
    // }
}
