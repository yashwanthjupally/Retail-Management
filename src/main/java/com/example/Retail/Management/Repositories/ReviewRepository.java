package com.example.Retail.Management.Repositories;

import com.example.Retail.Management.Models.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, Long> {

    List<Review> findByProductIdANDStoreId(Long storeId, Long productId);

}
