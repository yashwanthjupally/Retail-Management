package com.example.Retail.Management.Repositories;

import com.example.Retail.Management.Models.Inventory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query("SELECT i from Inventory WHERE i.product.id = :productId AND i.store.id = :storeId")
    Inventory findByProductIdAndStoreId(Long productId, Long storeId);

    List<Inventory> findByStoreId(Long storeId);

    @Modifying
    @Transactional
    void deleteByProductId(Long productId);
}
