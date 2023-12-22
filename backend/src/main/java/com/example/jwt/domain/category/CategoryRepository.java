package com.example.jwt.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query("SELECT c.name FROM Category c " +
            "JOIN c.products p " +
            "WHERE p.id = :productId")
    String findCategoryNameByProductId(UUID productId);
}
