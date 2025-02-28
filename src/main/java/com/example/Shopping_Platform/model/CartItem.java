package com.example.Shopping_Platform.model;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int i) {
        this.product=product;
        quantity=i;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
