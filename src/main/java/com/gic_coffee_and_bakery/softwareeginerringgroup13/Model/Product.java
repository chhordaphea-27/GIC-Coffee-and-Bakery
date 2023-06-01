package com.gic_coffee_and_bakery.softwareeginerringgroup13.Model;


public class Product extends Model {
    private String name;
    private String description;
    private String type;
    private Size size;
    private Category category;


    public Product(int productId, String name, String description, String type, Size size, Category category) {
        super(productId);
        this.name = name;
        this.description = description;
        this.type = type;
        this.size = size;
        this.category = category;
    }

    public Product() {
        // Default constructor
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

