package com.example.Retail.Management.Controllers;

import com.example.Retail.Management.Models.Customer;
import com.example.Retail.Management.Models.Review;
import com.example.Retail.Management.Repositories.CustomerRepository;
import com.example.Retail.Management.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/{storeId}/{productId}")
    public Map<String, Object> getReviews(@PathVariable Long storeId, @PathVariable Long productId){

        Map<String, Object> map = new HashMap<>();
        List<Review> reviews = reviewRepository.findByProductIdAndStoreId(storeId, productId);

        List<Map<String, Object>> reviewsWithCustomerNames = new ArrayList<>();

        for (Review review : reviews){
            Map<String, Object> reviewMap = new HashMap<>();
            reviewMap.put("review", review.getComment());
            reviewMap.put("rating", review.getRating());

            Customer customer = customerRepository.findByid(review.getCustomerId());
            if(customer != null){
                reviewMap.put("customerName", customer.getName());
            }else {
                reviewMap.put("customerName", "unknown");
            }
            reviewsWithCustomerNames.add(reviewMap);
        }
        map.put("reviews", reviewsWithCustomerNames);
        return map;
    }


    @GetMapping
    public Map<String,Object> getAllReviews()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("reviews",reviewRepository.findAll());
        return map;
    }


}
