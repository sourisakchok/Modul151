package com.example.testing.domain.product.unit;

import com.example.testing.domain.product.Product;
import com.example.testing.domain.product.ProductRepository;
import com.example.testing.domain.product.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplUnitTests {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product dummyProduct;
    private List<Product> dummyProducts;

    @BeforeEach
    public void setUp() {
        dummyProduct = new Product(UUID.randomUUID(), "kettle", 107);
        dummyProducts = Stream.of(new Product(UUID.randomUUID(), "shirt", 49), new Product(UUID.randomUUID(), "sandwich", 8)).collect(Collectors.toList());
    }

    @Test
    public void findById_requestProductById_expectProduct() throws Exception {
        given(productRepository.findById(any(UUID.class))).will(invocation -> {
            if ("non-existent".equals(invocation.getArgument(0)))
                throw new NoSuchElementException("No such product present");
            return Optional.of(dummyProduct);
        });

        assertThat(productService.findById(dummyProduct.getId())).usingRecursiveComparison().isEqualTo(dummyProduct);

        ArgumentCaptor<UUID> productArgumentCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(productRepository, times(1)).findById(productArgumentCaptor.capture());
        assertThat(productArgumentCaptor.getValue()).isEqualTo(dummyProduct.getId());
    }

    @Test
    @Disabled("Not implemented yet")
    public void accumulatedPriceOfAllProducts_provideProducts_expectCorrectPrice () {}

    @Test
    @Disabled("Not implemented yet - Recommended test method: Limit Tests")
    public void isPriceOfProductAbove_provideProduct_expectItToBeAboveGivenPrice () {}

}
