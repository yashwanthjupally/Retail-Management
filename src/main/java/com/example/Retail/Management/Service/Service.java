package com.example.Retail.Management.Service;

import com.example.Retail.Management.Models.Inventory;
import com.example.Retail.Management.Models.Product;
import com.example.Retail.Management.Repositories.InventoryRepository;
import com.example.Retail.Management.Repositories.ProductRepository;

@org.springframework.stereotype.Service
public class Service {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public Service(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    public boolean validateInventory(Inventory inventory){

        Inventory result = inventoryRepository.findByProductIdAndStoreId(inventory.getProduct().getId(),
                inventory.getStore().getId());

        if(result != null){
            return false;
        }
        return true;
    }


    public boolean validateProduct(Product product){

        Product result = productRepository.findByName(product.getName());

        if(result != null){
            return false;
        }
        return true;
    }

    public boolean validateProductId(Long id){

        Product result = productRepository.findByid(id);
        System.out.println(result);
        if(result == null){
            return false;
        }
        return true;
    }

    public Inventory getInventoryId(Inventory inventory){

        Inventory result = inventoryRepository.findByProductIdAndStoreId(inventory.getProduct().getId(),
                inventory.getStore().getId());

        return result;
    }
}