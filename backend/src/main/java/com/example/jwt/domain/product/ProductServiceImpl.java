package com.example.jwt.domain.product;

import java.util.*;

import com.example.jwt.core.generic.ExtendedRepository;
import com.example.jwt.core.generic.ExtendedServiceImpl;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ExtendedServiceImpl<Product> implements ProductService {

  private final ProductRepository productRepository;

  public ProductServiceImpl(ExtendedRepository<Product> repository, Logger logger, ProductRepository productRepository) {
    super(repository, logger);
    this.productRepository = productRepository;
  }

  @Override
  public Product save(Product entity) {
    return productRepository.save(entity);
  }

  @Override
  public Product findById(UUID id) {
    return findOrThrow(productRepository.findById(id));
  }

  @Override
  public List<Product> findAll(Pageable pageable) {
    Page<Product> pagedProduct = productRepository.findAll(pageable);
    return pagedProduct.hasContent() ? pagedProduct.getContent() : List.of();
  }

  @Override
  public Product updateById(UUID id, Product entity) throws NoSuchElementException {
    if (productRepository.existsById(id)) {
      entity.setId(id);
      return productRepository.save(entity);
    } else {
      throw new NoSuchElementException(String.format("Product with ID '%s' could not be found", id));
    }
  }

  @Override
  public Void deleteById(UUID id) throws NoSuchElementException {
    if (productRepository.existsById(id)) {
      productRepository.deleteById(id);
    } else {
      throw new NoSuchElementException(String.format("Product with ID '%s' could not be found", id));
    }
    return null;
  }

  @Override
  public boolean existsById(UUID id) {
    return productRepository.existsById(id);
  }

  @Override
  public Product findOrThrow(Optional<Product> optional) throws NoSuchElementException {
    if (optional.isPresent()) {
      return optional.get();
    } else {
      throw new NoSuchElementException("No value present");
    }
  }

  @Override
  public Product findMostExpensive(List<Product> products) {
    // Hier wird die Methode findAllProducts() aus dem Repository aufgerufen
    List<Product> allProducts = productRepository.findAllProducts();
    Optional<Product> mostExpensiveProduct = allProducts.stream()
            .max(Comparator.comparingDouble(Product::getSalePrice));
    return mostExpensiveProduct.orElse(null);
  }


}