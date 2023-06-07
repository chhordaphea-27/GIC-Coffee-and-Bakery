package com.gic_coffee_and_bakery.softwareeginerringgroup13.Model;

public class OrderItem extends Model {
    private Order order;
	private ProductSize productSize;
	private int quantity;
    private boolean cream;
    private String sugar;
    private String note;

    public OrderItem() {
        super();
    }

    public OrderItem(int orderItemId, Order order,  ProductSize productSize, int quantity, boolean cream, String sugar, String note) {
        super(orderItemId);
        this.order = order;
        this.productSize = productSize;
        this.quantity = quantity;
        this.cream = cream;
        this.sugar = sugar;
        this.note = note;
    }



    public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getCream() {
        return cream;
    }

    public void setCream(boolean cream) {
        this.cream = cream;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ProductSize getProductSize() {
		return productSize;
	}

	public void setProductSize(ProductSize productSize) {
		this.productSize = productSize;
	}


}
