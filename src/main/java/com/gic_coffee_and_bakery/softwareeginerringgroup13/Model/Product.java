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
