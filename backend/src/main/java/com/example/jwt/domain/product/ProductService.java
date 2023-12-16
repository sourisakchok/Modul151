package com.example.jwt.domain.product;

import com.example.jwt.core.generic.ExtendedService;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public interface ProductService extends ExtendedService<Product> {

  //Helper methods
  boolean existsById(UUID id);

  //Aggregate methods
  Product findMostExpensive(List<Product> products);
}