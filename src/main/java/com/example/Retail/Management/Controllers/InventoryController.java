package com.example.Retail.Management.Controllers;

import com.example.Retail.Management.Models.CombinedRequest;
import com.example.Retail.Management.Models.Inventory;
import com.example.Retail.Management.Models.Product;
import com.example.Retail.Management.Repositories.InventoryRepository;
import com.example.Retail.Management.Repositories.ProductRepository;
import com.example.Retail.Management.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private Service service;

    @PostMapping
    public Map<String, String> saveInventory(@RequestBody Inventory inventory){

        Map<String, String> map = new HashMap<>();
        try {
            if(service.validateInventory(inventory)){
                inventoryRepository.save(inventory);
            }else {
                map.put("message", "Data Already present in inventory");
                return map;
            }

        }catch (DataIntegrityViolationException e){
            map.put("message", "Error: " + e);
            System.out.println(e);
            return map;
        }
        catch (Exception e){
            map.put("message", "Error: " + e);
            System.out.println(e);
            return map;
        }
        map.put("message", "Product added to inventory successfully");
        return map;
    }

    @GetMapping("/{storeId}")
    public Map<String, Object> getAllProducts(@PathVariable Long storeId){
        Map<String, Object> map = new HashMap<>();
        List<Product> result = productRepository.findProductByStoreId(storeId);
        map.put("products", result);
        return map;
    }

    @GetMapping("filter/{category}/{name}/{storeId}")
    public Map<String, Object> getProductName(@PathVariable String category, @PathVariable String name,
                                              @PathVariable Long storeId){

        Map<String, Object> map = new HashMap<>();
        if(category.equals("null")){
            map.put("product", productRepository.findByNameLike(name, storeId));
            return map;
        }
        else if (name.equals("null")){
            map.put("product", productRepository.findByCategoryAndStoreId(category, storeId));
            return map;
        }
        map.put("product", productRepository.findByNameAndCategory(name, category, storeId));
        return map;
    }


    @GetMapping("search/{name}/{storeId}")
    public Map<String, Object> searchProduct(@PathVariable String name, @PathVariable Long storeId){

        Map<String, Object> map = new HashMap<>();
        map.put("product", productRepository.findByNameLike(name, storeId));
        return map;
    }


    @GetMapping("validate/{quantity}/{storeId}/{productId}")
    public boolean validateProduct(@PathVariable int quantity, @PathVariable Long productId,
                                   @PathVariable Long storeId){
        Inventory result = inventoryRepository.findByProductIdAndStoreId(productId, storeId);
        if(result.getStockLevel() >= quantity){
            return true;
        }
        return false;
    }


    @PutMapping
    public Map<String, String> updateInventory(@RequestBody CombinedRequest request){

        Product product = request.getProduct();
        Inventory inventory = request.getInventory();

        Map<String, String> map = new HashMap<>();
        System.out.println("Stock level: " + inventory.getStockLevel());
        if(!service.validateProductId(product.getId())){
            map.put("message", "Id " + product.getId() + " not present in database");
            return map;
        }
        productRepository.save(product);
        map.put("message", "successfully updated product with Id " + product.getId());

        if(inventory != null){
            try {
                Inventory result = service.getInventoryId(inventory);
                if(result != null){
                    inventory.setId(result.getId());
                    inventoryRepository.save(inventory);
                }else {
                    map.put("message", "No data available for this product or store id");
                    return map;
                }
            }catch (DataIntegrityViolationException e){
                map.put("message", "Error: " + e);
                System.out.println(e);
                return map;
            }
            catch (Exception e){
                map.put("message", "Error: " + e);
                System.out.println(e);
                return map;
            }
        }
        return map;
    }


    @DeleteMapping("/{id}")
    public Map<String, String> removeProduct(Long id){
        Map<String, String> map = new HashMap<>();
        if(!service.validateProductId(id)){
            map.put("message", "Id " + id + " not present in database");
            return map;
        }
        inventoryRepository.deleteByProductId(id);
        map.put("message", "Deleted product successfully with id: " + id);
        return map;
    }
  }
