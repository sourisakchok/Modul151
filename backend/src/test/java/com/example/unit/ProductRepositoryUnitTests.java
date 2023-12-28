//package com.example.unit;
//
//import com.example.jwt.JwtApplication;
//import com.example.jwt.domain.product.Product;
//import com.example.jwt.domain.product.ProductRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//
//@SpringBootTest(classes = JwtApplication.class)
//@ActiveProfiles("test")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//public class ProductRepositoryUnitTests {
//
//    @MockBean
//    private ProductRepository productRepository;
//
//    private List<Product> dummyProducts;
//
//    @BeforeEach
//    public void setUp() {
//        dummyProducts = Stream.of(
//                new Product(UUID.randomUUID(), "shirt", null, null, 30, 49, LocalDate.now(), null),
//                new Product(UUID.randomUUID(), "sandwich", null, null, 5, 8, LocalDate.now(), null),
//                new Product(UUID.randomUUID(), "Snowflake", null, null, 10.99, 15, LocalDate.now(), null)
//        ).collect(Collectors.toList());
//
//        given(productRepository.findAll()).willReturn(dummyProducts);
//
//        given(productRepository.findById(any(UUID.class)))
//                .willAnswer(invocation -> {
//                    UUID id = invocation.getArgument(0);
//                    Optional<Product> optionalProduct = dummyProducts.stream()
//                            .filter(product -> product.getId().equals(id))
//                            .findFirst();
//                    return optionalProduct.map(Optional::of).orElseGet(Optional::empty);
//                });
//
//        given(productRepository.save(any(Product.class)))
//                .willAnswer(invocation -> {
//                    Product savedProduct = invocation.getArgument(0);
//                    // Mocking the save behavior, e.g., setting an ID
//                    return new Product(UUID.randomUUID(), savedProduct.getName(), null, null,
//                            savedProduct.getPurchasePrice(), savedProduct.getSalePrice(),
//                            LocalDate.now(), null);
//                });
//    }
//
//    @Test
//    public void findById_requestProductById_expectProduct() {
//        assertThat(productRepository.findById(dummyProducts.get(0).getId())).hasValue(dummyProducts.get(0));
//    }
//
//    @Test
//    public void findAll_requestAllProducts_expectAllProducts() {
//        assertThat(productRepository.findAll()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(dummyProducts);
//    }
//}
