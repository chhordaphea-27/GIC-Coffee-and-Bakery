package com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.Category;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.Product;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.ProductSize;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.Size;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductSizeListManagement extends Management<ProductSize> {
    @Override
    protected ProductSize mapRowToModel(ResultSet rs) throws SQLException {
        int id = rs.getInt("product_id");
        String name = rs.getString("name");
        String description = rs.getString("description");

        int categoryId = rs.getInt("category_id");
        Category category = getCategoryById(categoryId);

        String imageUrl = rs.getString("image_url");
        java.sql.Date lastOrder = rs.getDate("last_order");

        Product product = new Product(id, name, description, category, imageUrl, lastOrder);
        // Map other product properties as needed
        
        Size size = new Size();
        size.setId(rs.getInt("size_id"));
        size.setSizeName(rs.getString("size_name"));
        size.setPrice(rs.getDouble("size_price"));
        // Map other size properties as needed
        
        // Create the ProductSize model and set the product and size
        ProductSize productSize = new ProductSize();
        productSize.setProduct(product);
        productSize.setSize(size);
        
        return productSize;
    }

    @Override
    protected void setStatementParams(Boolean isAddOperation, PreparedStatement stmt, ProductSize model) throws SQLException {
        // Set the statement parameters based on the model properties
        Product product = model.getProduct();
        Size size = model.getSize();
        
        // Set the parameters for the product_size table
        stmt.setInt(1, size.getId());
        stmt.setInt(2, product.getId());
        
        // Set other parameters as needed for the add operation
    }

    public int add(ProductSize model) {
        String query = "INSERT INTO product_size (size_id, product_id) VALUES (?, ?)";
        return super.add(model, query);
    }

    public void update(ProductSize model) {
        String query = "UPDATE product_size SET size_id = ? WHERE product_id = ?";
        super.update(model, query);
    }

    public void delete(ProductSize productSize) {
        String query = "DELETE FROM product_size WHERE product_size_id = ?";
        super.delete(productSize.getId(), query);
    }
    
    public void disable(int productId, int sizeId) {
        // You can implement disabling logic specific to your application
    }

    public List<ProductSize> query(String keyword) {
        String query = "SELECT * FROM product_size WHERE product_id IN (SELECT product_id FROM product WHERE name LIKE ?) OR size_id IN (SELECT size_id FROM size WHERE size_name LIKE ?)";
        return super.query(keyword, query, "%" + keyword + "%", "%" + keyword + "%");
    }






    private Category getCategoryById(int categoryId) {
        CategoryManagement categoryManagement = new CategoryManagement();
        return categoryManagement.getCategoryById(categoryId);
    }
}

