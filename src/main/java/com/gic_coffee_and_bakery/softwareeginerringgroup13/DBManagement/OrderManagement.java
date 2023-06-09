package com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.Order;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.Table;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.User;

public class OrderManagement extends Management<Order> {
    private static final String ADD_ORDER_QUERY = "INSERT INTO `order` (user_id, date_created, status, table_id, total_price) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_ORDER_QUERY = "UPDATE `order` SET user_id = ?, date_created = ?, status = ?, table_id = ?, total_price = ? WHERE order_id = ?";
    private static final String DELETE_ORDER_QUERY = "DELETE FROM `order` WHERE order_id = ?";
    private static final String DISABLE_ORDER_QUERY = "UPDATE `order` SET status = 'Disabled' WHERE order_id = ?";
    private static final String GET_ALL_ORDERS_QUERY = "SELECT * FROM `order`";
    private static final String GET_ORDER_BY_ID_QUERY = "SELECT * FROM `order` WHERE order_id = ?";
    private static final String SEARCH_ORDERS_QUERY = "SELECT * FROM `order` WHERE status LIKE ?";

    @Override
    protected Order mapRowToModel(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("order_id"));
        order.setUser(getUserByID(rs.getInt("user_id")));
        order.setDateCreated(rs.getTimestamp("date_created"));
        order.setStatus(rs.getString("status"));
        order.setTable(getTableById(rs.getInt("table_id")));
        order.setTotalPrice(rs.getDouble("total_price"));

        return order;
    }

    
    @Override
    protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, Order order) throws SQLException {
        // Set the statement parameters based on the order properties
        stmt.setInt(1, order.getUser().getId());
        stmt.setTimestamp(2, order.getDateCreated());
        stmt.setString(3, order.getStatus());
        stmt.setInt(4, order.getTable().getId());
        stmt.setDouble(5, order.getTotalPrice());

        if (!isAddOperation) {
            stmt.setInt(6, order.getId());
        }
    }

    public int addOrder(Order order) {
        return add(order, ADD_ORDER_QUERY);
    }

    public void updateOrder(Order order) {
        update(order, UPDATE_ORDER_QUERY);
    }

    public void deleteOrder(int orderId) {
        delete(orderId, DELETE_ORDER_QUERY);
    }

    public void disableOrder(int orderId) {
        disable(orderId, DISABLE_ORDER_QUERY);
    }

    public List<Order> getAllOrders() {
        return getAll(GET_ALL_ORDERS_QUERY);
    }

    public Order getOrderById(int orderId) {
        return getById(orderId, GET_ORDER_BY_ID_QUERY);
    }

    public List<Order> searchOrders(String statusKeyword) {
        String searchKeyword = "%" + statusKeyword + "%";
        return query(searchKeyword, SEARCH_ORDERS_QUERY, searchKeyword);
    }

    private User getUserByID(int user_id) {
        UserManagement userManagement = new UserManagement();
        return userManagement.getUserById(user_id);
    }

    private Table getTableById(int table_id) {
        TableManagement tableManagement = new TableManagement();
        return tableManagement.getTableById(table_id);
    }

}
