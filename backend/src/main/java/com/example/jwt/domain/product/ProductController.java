package com.example.jwt.domain.product;

import com.example.jwt.domain.product.dto.ProductMapper;
import com.example.jwt.domain.product.dto.ProductDTO;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import com.example.jwt.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

  private final ProductService productService;

  private final ProductMapper productMapper;

  private final UserService userService;

  @Autowired
  public ProductController(ProductService productService, ProductMapper productMapper, UserService userService) {
    this.productService = productService;
    this.productMapper = productMapper;
    this.userService = userService;
  }

  @PostMapping({"", "/"})
  public ResponseEntity<Product> create(@Valid @RequestBody ProductDTO productDTO) {
    Product product = productMapper.productDTOToProduct(productDTO);
    return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> retrieveById(@PathVariable UUID id) {
    return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
  }
// Muss wie folgt aufgerufen werden /products?page=0&size=5
  @GetMapping("")
  @PreAuthorize("hasAuthority('CAN_RETRIEVE_PRODUCTS')")
  public ResponseEntity<List<Product>> retrieveAll(Pageable pageable) {
    return new ResponseEntity<>(productService.findAll(pageable), HttpStatus.OK);
  }

  @PutMapping({"", "/"})
  public ResponseEntity<Product> updateById(@RequestBody Product product) {
    return new ResponseEntity<>(productService.updateById(product.getId(), product), HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Product> partialUpdateById(@RequestBody Product product) {
    return new ResponseEntity<>(productService.updateById(product.getId(), product), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/price/highest")
  public ResponseEntity<Product> retrieveMostExpensive(Pageable pageable) {
    List<Product> allProducts = productService.findAll(pageable);
    return new ResponseEntity<>(productService.findMostExpensive(allProducts), HttpStatus.OK);
  }
}