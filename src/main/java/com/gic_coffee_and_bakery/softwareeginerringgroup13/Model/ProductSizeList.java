package com.gic_coffee_and_bakery.softwareeginerringgroup13.Model;

import java.util.List;

public class ProductSizeList extends Model {
    private Product product;
    private List<Size> sizes;
    
	public ProductSizeList() {
	}

	public ProductSizeList(Product product, List<Size> sizes) {
		this.product = product;
		this.sizes = sizes;
	}
    
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Size> getSizes() {
		return sizes;
	}
	public void setSizes(List<Size> sizes) {
		this.sizes = sizes;
	}
}
