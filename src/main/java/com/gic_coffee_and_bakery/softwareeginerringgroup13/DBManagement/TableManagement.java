package com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement;


import java.sql.*;
import java.util.List;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.Table;

public class TableManagement extends Management<Table> {

    @Override
    protected Table mapRowToModel(ResultSet rs) throws SQLException {
        Table table = new Table();
        table.setId(rs.getInt("table_id"));
        table.setTableNumber(rs.getInt("tableNumber"));
        table.setTableStatus(rs.getString("table_statue"));
        return table;
    }
        

    @Override
    protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, Table table) throws SQLException {
        stmt.setInt(1, table.getTableNumber());
        stmt.setString(3, table.getTableStatus());

        if (!isAddOperation) {
            stmt.setInt(4, table.getId());
        }
    }

    public int addTable(Table table) {
        String query = "INSERT INTO `table` (tableNumber, table_type, table_statue) VALUES (?, ?, ?)";
        return add(table, query);
    }

    public void updateTable(Table table) {
        String query = "UPDATE `table` SET tableNumber = ?, table_type = ?, table_statue = ? WHERE table_id = ?";
        update(table, query);
    }

    public List<Table> getAllTables() {
        String query = "SELECT * FROM `table`";
        return getAll(query);
    }

    public Table getTableById(int tableId) {
        String query = "SELECT * FROM `table` WHERE table_id = ?";
        return getById(tableId, query);
    }

    public void deleteTable(int tableId) {
        String query = "DELETE FROM `table` WHERE table_id = ?";
        delete(tableId, query);
    }
}
