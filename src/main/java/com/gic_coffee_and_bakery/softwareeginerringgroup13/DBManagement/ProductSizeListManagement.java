package com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement;

import java.util.ArrayList;
import java.util.List;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.*;



public class ProductSizeListManagement extends ProductSizeManagement {

    ProductManagement productManagement = new ProductManagement();
    ProductSizeManagement productSizeManagement = new ProductSizeManagement();
    SizeManagement sizeManagement = new SizeManagement();

    
    public ProductSizeList getProductSizeListByProductID(int product_id) {

        var product = productManagement.getProductById(product_id);

        ProductSizeList productSizeList = new ProductSizeList();
        productSizeList.setProduct(product);    


        ArrayList<Size> sizes = new ArrayList<Size>();

        var productsList = productSizeManagement.getProductSizesByProductId(product.getId());
        for (ProductSize productSize2 : productsList) {
            sizes.add(productSize2.getSize());
        }
        productSizeList.setSizes(sizes);

        return productSizeList;
    }


    public List<ProductSizeList> getAllProductSizeList() {
        ArrayList<ProductSizeList> productSizeLists = new ArrayList<>();
        
        var products = productManagement.getAllProducts();
        for (Product product : products) {
            productSizeLists.add(getProductSizeListByProductID(product.getId()));
        }

        return productSizeLists;
    }

    public List<ProductSizeList> getProductSizeListByCategoryID(int category_id) {
        ArrayList<ProductSizeList> productSizeLists = new ArrayList<>();
        List<Product> productInCate = productManagement.getProductsByCategory(category_id);


        for (Product product : productInCate) {
            productSizeLists.add(getProductSizeListByProductID(product.getId()));
        }

        return productSizeLists;
    }


    public List<ProductSizeList> getProductSizeListByType(String type) {
        ArrayList<ProductSizeList> productSizeLists = new ArrayList<>();

        CategoryManagement categoryManagement = new CategoryManagement();
        if (type.equals("Food")) {
            List<Category> foodCates = categoryManagement.getAllFoodCategories();
            for (Category category : foodCates) {
                var productInCate = productManagement.getProductsByCategory(category.getId());
                
                for (Product product : productInCate) {
                    productSizeLists.add(getProductSizeListByProductID(product.getId()));
                }
            }
        } else {
            List<Category> foodCates = categoryManagement.getAllDrinkCategories();
            for (Category category : foodCates) {
                var productInCate = productManagement.getProductsByCategory(category.getId());
                
                for (Product product : productInCate) {
                    productSizeLists.add(getProductSizeListByProductID(product.getId()));
                }
            }
        } 
        

        return productSizeLists;
    }



}
