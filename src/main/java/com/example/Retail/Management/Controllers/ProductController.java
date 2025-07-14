package com.example.Retail.Management.Controllers;

import com.example.Retail.Management.Models.Product;
import com.example.Retail.Management.Repositories.InventoryRepository;
import com.example.Retail.Management.Repositories.ProductRepository;
import com.example.Retail.Management.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private Service service;

    @PostMapping()
    public Map<String, String> addProduct(@RequestBody Product product){

        Map<String, String> map = new HashMap<>();
        if(!service.validateProduct(product)){
            map.put("message", "product already present in database");
        }
        else {
            productRepository.save(product);
            map.put("message", "Product added successfully");
        }
        return map;
    }


    @GetMapping("/{id}")
    public Map<String, Object> findById(@PathVariable Long id){

        Map<String, Object> map = new HashMap<>();
        Product result = productRepository.findByid(id);
        System.out.println("result:" + result);
        map.put("products", result);
        return map;
    }

    @PutMapping
    public Map<String, String> updateProduct(@RequestBody Product product){
        Map<String, String> map = new HashMap<>();
        try {
            productRepository.save(product);
            map.put("message", "Data updated successfully");
        } catch (Error e) {
            map.put("message", "Error occurred");
        }
        return map;
    }

    @GetMapping("/category/{category}")
    public Map<String, Object> filterByCategory(@PathVariable String category){
        Map<String, Object> map = new HashMap<>();
        if(category.equals("null"))
        {
            map.put("products", "No such category exits");
            return map;
        }
        map.put("products",productRepository.findByCategory(category));
        return map;
    }

    @GetMapping("/price/{min}-{max}")
    public Map<String, Object> filterByPrice(@PathVariable Double min, @PathVariable Double max) {
        Map<String, Object> map = new HashMap<>();
        map.put("products", productRepository.findByPriceBetween(min, max));
        return map;
    }

    @GetMapping
    public Map<String, Object> listProduct() {
        Map<String, Object> map = new HashMap<>();
        map.put("products",productRepository.findAll());
        return map;
    }


    @GetMapping("filter/{category}/{storeid}")
    public Map<String, Object> getProductbyCategoryAndStoreId(@PathVariable String category,@PathVariable long storeid) {
        Map<String, Object> map = new HashMap<>();
        List<Product> result = productRepository.findProductByCategory(category,storeid);
        map.put("product", result);
        return map;
    }

    @GetMapping("/searchProduct/{name}")
    public Map<String, Object> searchProduct(@PathVariable String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("products", productRepository.findProductBySubName(name));
        return map;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteProduct(@PathVariable Long id){

       Map<String, String> map = new HashMap<>();
       if(service.validateProductId(id)){
           inventoryRepository.deleteByProductId(id);
           productRepository.deleteById(id);
           map.put("message", "product" + "id "+ id + "deleted successfully");
       }
       else {
           map.put("message", "product" + "id " + id + "didn't exist");
       }
       return map;
    }

}
