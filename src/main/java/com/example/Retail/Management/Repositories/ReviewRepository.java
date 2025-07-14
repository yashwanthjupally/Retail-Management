package com.example.Retail.Management.Repositories;

import com.example.Retail.Management.Models.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ReviewRepository extends MongoRepository<Review, String> {

    List<Review> findByProductIdAndStoreId(Long storeId, Long productId);

}
