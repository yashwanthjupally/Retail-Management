package com.example.Retail.Management.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer stockLevel;

    @ManyToOne
    @JsonBackReference("inventory-product")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JsonBackReference("inventory-store")
    @JoinColumn(name = "store_id")
    private Store store;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Integer getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(Integer stockLevel) {
        this.stockLevel = stockLevel;
    }

    public Inventory() {
    }

    public Inventory(Product product, Store store, Integer stockLevel) {
        this.product = product;
        this.store = store;
        this.stockLevel = stockLevel;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", stockLevel=" + stockLevel +
                ", product=" + (product != null ? product.getId() : "null") +
                ", store=" + (store != null ? store.getId() : "null") +
                '}';
    }
}
