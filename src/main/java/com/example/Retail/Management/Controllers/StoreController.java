package com.example.Retail.Management.Controllers;

import com.example.Retail.Management.DTO.PlaceOrderRequestDTO;
import com.example.Retail.Management.Models.Store;
import com.example.Retail.Management.Repositories.StoreRepository;
import com.example.Retail.Management.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    OrderService orderService;

    @PostMapping
    public Map<String, String> addStore(@RequestBody Store store) {
        Store savedStore = storeRepository.save(store);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Store added successfully with id "+ savedStore.getId());
        return map;
    }

    @GetMapping("validate/{storeId}")
    public boolean validateStore(@PathVariable Long storeId){

        Store store = storeRepository.findByid(storeId);
        if(store != null){
            return true;
        }
        return false;
    }

    @PostMapping("/placeOrder")
    public Map<String, String> placeOrder(@RequestBody PlaceOrderRequestDTO placeOrderRequestDTO){

        Map<String, String> map = new HashMap<>();
        try {
            orderService.saveOrder(placeOrderRequestDTO);
            map.put("message", "Order placed successfully");
        }
        catch (Error e){
            map.put("Error",""+e);
        }
        return map;
    }

}
