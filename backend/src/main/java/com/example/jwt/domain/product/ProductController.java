package com.example.jwt.domain.product;

import com.example.jwt.domain.product.dto.ProductMapper;
import com.example.jwt.domain.product.dto.ProductDTO;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @Autowired
  public ProductController(ProductService productService, ProductMapper productMapper) {
    this.productService = productService;
    this.productMapper = productMapper;
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

  @GetMapping({"{page}/{pagesize}", "{page}/{pagesize}"})
  public ResponseEntity<List<Product>> retrieveAll(@PathVariable int page, @PathVariable int pagesize) {
    return new ResponseEntity<>(productService.findAll((PageRequest.of( page, pagesize, Sort.by("price").descending()))), HttpStatus.OK);
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
