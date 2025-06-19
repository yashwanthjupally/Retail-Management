package com.example.Retail.Management.DTO;

import java.util.List;

public class PlaceOrderRequestDTO {

    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private Long storeId;
    private Double totalPrice;
    private List<PurchaseProductDto> purchasePriceProduct;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<PurchaseProductDto> getPurchasePriceProduct() {
        return purchasePriceProduct;
    }

    public void setPurchasePriceProduct(List<PurchaseProductDto> purchasePriceProduct) {
        this.purchasePriceProduct = purchasePriceProduct;
    }
}
