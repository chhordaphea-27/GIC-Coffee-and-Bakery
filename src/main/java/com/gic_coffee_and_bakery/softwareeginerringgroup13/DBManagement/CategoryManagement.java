package com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.Category;

public class CategoryManagement extends Management<Category> {
    private static final String ADD_CATEGORY_QUERY = "INSERT INTO category (category_name, type, image_url) VALUES (?, ?, ?)";
    private static final String UPDATE_CATEGORY_QUERY = "UPDATE category SET category_name = ?, type = ?, image_url = ? WHERE category_id = ?";
    private static final String DELETE_CATEGORY_QUERY = "DELETE FROM category WHERE category_id = ?";
    private static final String DISABLE_CATEGORY_QUERY = "UPDATE category SET status = 'Disabled' WHERE category_id = ?";
    private static final String GET_ALL_CATEGORIES_QUERY = "SELECT * FROM category";
    private static final String GET_CATEGORY_BY_ID_QUERY = "SELECT * FROM category WHERE category_id = ?";
    private static final String SEARCH_CATEGORY_QUERY = "SELECT * FROM category WHERE category_name LIKE ? OR type LIKE ?";

    @Override
    protected Category mapRowToModel(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("category_id"));
        category.setCategoryName(rs.getString("category_name"));
        category.setType(rs.getString("type"));
        category.setImage_url(rs.getString("image_url"));
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

	@Override
	protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, Category model)
			throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setStatementParams'");
	}
}
