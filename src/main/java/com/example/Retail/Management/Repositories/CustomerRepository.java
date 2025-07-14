package com.example.Retail.Management.Repositories;

import com.example.Retail.Management.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

     Customer findByEmail(String email);

    Customer findByid(Long id);
}
