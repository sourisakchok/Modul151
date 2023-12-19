package com.example.testing.domain.product.unit;

import com.example.testing.domain.product.Product;
import com.example.testing.domain.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductRepositoryUnitTests {

    @Autowired
    ProductRepository productRepository;

    private List<Product> dummyProducts;

    @BeforeEach
    public void setUp() {
        dummyProducts = productRepository.saveAll(Stream.of(new Product(UUID.randomUUID(), "shirt", 49), new Product(UUID.randomUUID(), "sandwich", 8)).collect(Collectors.toList()));
    }

    @Test
    public void findById_requestProductById_expectProduct() {
        assertThat(productRepository.findById(dummyProducts.get(0).getId())).hasValue(dummyProducts.get(0));
    }

    @Test
    public void findAll_requestAllProducts_expectAllProducts() {
        assertThat(productRepository.findAll()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(dummyProducts);
    }

    @Test
    @Disabled("Not implemented yet")
    public void save_requestProductToBeSaved_expectSavedProduct() {}


}
