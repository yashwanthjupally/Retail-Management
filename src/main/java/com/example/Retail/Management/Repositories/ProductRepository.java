package com.example.Retail.Management.Repositories;

import com.example.Retail.Management.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findBySKU(String sku);

    Product findByName(String name);

    Product findByid(Long id);

    @Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND i.product.category = :category")
    List<Product> findByNameLike(String pname, Long storeId);

    @Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND " +
            "LOWER(i.product.name) LIKE LOWER(CONCAT('%', :pname, '%')) AND " +
            "i.product.category = :category")
    List<Product> findByNameAndCategory(String pname, String category, Long storeId);

    @Query("SELECT i.product from Inventory i WHERE i.product.category = :category AND i.store.id = :storeId")
    List<Product> findByCategoryAndStoreId(String category, Long storeId);

    @Query("SELECT i FROM Product i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :pname, '%'))")
    List<Product> findProductBySubName(String pname);

    @Query("SELECT i.product from Inventory i WHERE i.store.id = :storeId")
    List<Product> findProductByStoreId(Long storeId);

    @Query("SELECT i.product from Inventory i WHERE i.product.category = :category AND i.store.id = :storeId")
    List<Product> findProductByCategory(String category, Long storeId);

    @Query("SELECT i from Product i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :pname, '%')) AND i.category = :category")
    List<Product> findProductBySubNameAndCategory(String pname, String category);

}
