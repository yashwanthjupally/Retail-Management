package com.example.Retail.Management.Repositories;

import com.example.Retail.Management.Models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByid(Long id);
    @Query
    List<Store> findBySubname(String name);
}
