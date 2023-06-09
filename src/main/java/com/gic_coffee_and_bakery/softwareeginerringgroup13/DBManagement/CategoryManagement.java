package com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.Category;

public class CategoryManagement extends Management<Category> {
    private static final String ADD_CATEGORY_QUERY = "INSERT INTO category (category_name, type) VALUES (?, ?)";
    private static final String UPDATE_CATEGORY_QUERY = "UPDATE category SET category_name = ?, type = ? WHERE category_id = ?";
    private static final String DELETE_CATEGORY_QUERY = "DELETE FROM category WHERE category_id = ?";
    private static final String DISABLE_CATEGORY_QUERY = "UPDATE category SET type = 'Disabled' WHERE category_id = ?";
    private static final String GET_ALL_CATEGORIES_QUERY = "SELECT * FROM category WHERE type <> 'Disabled'";
    private static final String GET_CATEGORY_BY_ID_QUERY = "SELECT * FROM category WHERE category_id = ?";
    private static final String SEARCH_CATEGORY_QUERY = "SELECT * FROM category WHERE category_name LIKE ? OR type LIKE ?";

    @Override
    protected Category mapRowToModel(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("category_id"));
        category.setCategoryName(rs.getString("category_name"));
        category.setType(rs.getString("type"));
        return category;
    }

    public int addCategory(Category category) {
        return add(category, ADD_CATEGORY_QUERY);
    }

    public void updateCategory(Category category) {
        update(category, UPDATE_CATEGORY_QUERY);
    }

    public void deleteCategory(int categoryId) {
        delete(categoryId, DELETE_CATEGORY_QUERY);
    }

    public void disableCategory(int categoryId) {
        disable(categoryId, DISABLE_CATEGORY_QUERY);
    }

    public List<Category> getAllCategories() {
        return getAll(GET_ALL_CATEGORIES_QUERY);
    }

    public Category getCategoryById(int categoryId) {
        return getById(categoryId, GET_CATEGORY_BY_ID_QUERY);
    }

    public List<Category> searchCategories(String keyword) {
        String searchKeyword = "%" + keyword + "%";
        return query(searchKeyword, SEARCH_CATEGORY_QUERY, searchKeyword, searchKeyword);
    }

    public List<Category> getAllFoodCategories() {
        String query = "SELECT * FROM category WHERE type = ?";
        return query("Keyword", query, "Food");
    }

    public List<Category> getAllDrinkCategories() {
        String query = "SELECT * FROM category WHERE type = ?";
        return query("Keyword", query, "Drink");
    }

    @Override
    protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, Category category) throws SQLException {
        stmt.setString(1, category.getCategoryName());
        stmt.setString(2, category.getType());

        if (!isAddOperation) {
            stmt.setInt(3, category.getId());
        }
    }
}
