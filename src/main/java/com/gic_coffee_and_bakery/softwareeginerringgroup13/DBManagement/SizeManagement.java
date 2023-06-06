package com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class SizeManagement extends Management<Size> {

    @Override
    protected Size mapRowToModel(ResultSet rs) throws SQLException {
        int sizeId = rs.getInt("size_id");
        String sizeName = rs.getString("size_name");
        double price = rs.getDouble("price");

        return new Size(sizeId, sizeName, price);
    }

    @Override
    protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, Size size) throws SQLException {
        stmt.setString(1, size.getSizeName());
        stmt.setDouble(2, size.getPrice());

        if (!isAddOperation) {
            stmt.setInt(3, size.getId());
        }
    }

    public int addSize(Size size) {
        String query = "INSERT INTO size (size_name, price) VALUES (?, ?)";
        return add(size, query);
    }

    public void updateSize(Size size) {
        String query = "UPDATE size SET size_name = ?, price = ? WHERE size_id = ?";
        update(size, query);
    }

    public List<Size> getAllSizes() {
        String query = "SELECT * FROM size";
        return getAll(query);
    }

    public Size getSizeById(int id) {
        String query = "SELECT * FROM size WHERE size_id = ?";
        return getById(id, query);
    }

    public List<Size> querySizes(String keyword) {
        String query = "SELECT * FROM size WHERE size_name LIKE ?";
        return query(keyword, query);
    }
}
