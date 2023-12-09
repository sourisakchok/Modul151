package com.example.jwt.domain.product;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
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

  // As soon as we got a database connected to our application, we can fetch the most expensive product from the database
  // and return it. For now, we just return the most expensive product from a list of products!
  @Override
  public Product findMostExpensive(List<Product> products) {
    // Hier wird die Methode findAllProducts() aus dem Repository aufgerufen
    List<Product> allProducts = productRepository.findAllProducts();
    Optional<Product> mostExpensiveProduct = allProducts.stream()
            .max(Comparator.comparingDouble(Product::getPrice));
    return mostExpensiveProduct.orElse(null);
  }

}
