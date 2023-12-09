package com.example.jwt.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    // Native Queries
    @Query(value = "SELECT * FROM Product", nativeQuery = true)
    List<Product> findAllProducts();

    // Derivies QUery
    @Query(value = "SELECT * FROM Product WHERE price = 99", nativeQuery = true)
    List<Product> findAllProductsByPrice();
}
