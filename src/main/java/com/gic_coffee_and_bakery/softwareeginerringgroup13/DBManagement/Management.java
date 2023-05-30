package com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Management<T> {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gic_coffee_and_bakery";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    protected abstract T mapRowToModel(ResultSet rs) throws SQLException;

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public void add(T model, String query) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the parameters for the prepared statement using model's data

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(T model, String query) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the parameters for the prepared statement using model's data

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<T> getAll(String query) {
        List<T> modelList = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                T model = mapRowToModel(rs);
                modelList.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelList;
    }

    public T getById(int id, String query) {
        T model = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    model = mapRowToModel(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }

    public List<T> query(String keyword, String query) {
        List<T> modelList = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + keyword + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    T model = mapRowToModel(rs);
                    modelList.add(model);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelList;
    }
}