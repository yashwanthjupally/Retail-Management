package com.example.Retail.Management.Service;

import com.example.Retail.Management.DTO.PlaceOrderRequestDTO;
import com.example.Retail.Management.DTO.PurchaseProductDto;
import com.example.Retail.Management.Models.*;
import com.example.Retail.Management.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public void saveOrder(PlaceOrderRequestDTO placeOrderRequest){

        Customer existingCustomer = customerRepository.findByEmail(placeOrderRequest.getCustomerEmail());
        Customer customer = new Customer();
        customer.setName(placeOrderRequest.getCustomerName());
        customer.setEmail(placeOrderRequest.getCustomerEmail());
        customer.setPhone(placeOrderRequest.getCustomerPhone());

        if(existingCustomer == null){
            customer = customerRepository.save(customer);
        }else {
            customer = existingCustomer;
        }

        Store store = storeRepository.findById(placeOrderRequest.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCustomer(customer);
        orderDetails.setStore(store);
        orderDetails.setTotalPrice(placeOrderRequest.getTotalPrice());
        orderDetails.setDate(LocalDateTime.now());

        orderDetails = orderDetailsRepository.save(orderDetails);


        List<PurchaseProductDto> purchaseProducts = placeOrderRequest.getPurchasePriceProduct();
        for(PurchaseProductDto productDto : purchaseProducts){
            OrderItem orderItem = new OrderItem();

            Inventory inventory = inventoryRepository.findByProductIdAndStoreId(productDto.getId(),
                    placeOrderRequest.getStoreId());

            inventory.setStockLevel(inventory.getStockLevel() - productDto.getQuantity());
            inventoryRepository.save(inventory);

            orderItem.setOrder(orderDetails);

            orderItem.setProduct(productRepository.findByid(productDto.getId()));

            orderItem.setQuantity(productDto.getQuantity());
            orderItem.setPrice(productDto.getPrice()*productDto.getQuantity());

            orderItemRepository.save(orderItem);

        }

    }
}