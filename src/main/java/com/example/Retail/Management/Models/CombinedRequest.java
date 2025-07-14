package com.example.Retail.Management.Models;

public class CombinedRequest {

    private Product product;
    private Inventory inventory;

    public CombinedRequest(Product product, Inventory inventory) {
        this.product = product;
        this.inventory = inventory;
    }

    public CombinedRequest() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
