//package com.example.unit;
//
//import com.example.jwt.JwtApplication;
//import com.example.jwt.core.security.helpers.AuthorizationSchemas;
//import com.example.jwt.core.security.helpers.JwtProperties;
//import com.example.jwt.domain.authority.Authority;
//import com.example.jwt.domain.product.Product;
//import com.example.jwt.domain.product.ProductService;
//import com.example.jwt.domain.role.Role;
//import com.example.jwt.domain.user.User;
//import com.example.jwt.domain.user.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.hamcrest.Matchers.hasSize;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//@SpringBootTest(classes = JwtApplication.class)
//@ActiveProfiles("test")
//@AutoConfigureMockMvc()
//public class ProductControllerUnitTests {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private JwtProperties jwtProperties;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private ProductService productService;
//
//    private String dummyToken;
//    private Product dummyProduct;
//    private List<Product> dummyProducts;
//
//    private String generateToken() {
//        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
//
//        return Jwts.builder()
//                .setClaims(Map.of("sub", UUID.randomUUID()))
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMillis()))
//                .setIssuer(jwtProperties.getIssuer())
//                .signWith(Keys.hmacShaKeyFor(keyBytes))
//                .compact();
//    }
//
//    @BeforeEach
//    public void setUp() {
//        dummyToken = generateToken();
//        dummyProduct = new Product("kettle", null, null, 0, 107, null, null);
//        dummyProducts = Stream.of(
//                new Product("shirt", null, null, 0, 49, null, null),
//                new Product("sandwich", null, null, 0, 8, null, null)
//        ).collect(Collectors.toList());
//    }
//
//    @Test
//    public void retrieveAll_requestAllProducts_expectAllProductsAsDTOS() throws Exception {
//        given(userService.findById(any(UUID.class))).willReturn(
//                new User().setRoles(new Role().setAuthorities(Set.of(new Authority().setName("USER_READ")))));
//        given(productService.findAll()).willReturn(dummyProducts);
//
//        mvc.perform(MockMvcRequestBuilders
//                        .get("/products")
//                        .header(HttpHeaders.AUTHORIZATION, AuthorizationSchemas.BEARER + " " + dummyToken)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").value(Matchers.containsInAnyOrder(dummyProducts.get(0).getId().toString(), dummyProducts.get(1).getId().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name").value(Matchers.containsInAnyOrder(dummyProducts.get(0).getName(), dummyProducts.get(1).getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[*].price").doesNotExist());
//
//        verify(productService, times(1)).findAll();
//    }
//
//    @Test
//    public void retrieveById_requestProductById_expectProductAsDTO() throws Exception {
//        given(userService.findById(any(UUID.class))).willReturn(new User());
//        given(productService.findById(any(UUID.class))).will(invocation -> {
//            if ("non-existent".equals(invocation.getArgument(0)))
//                throw new NoSuchElementException("No such product present");
//            return dummyProduct;
//        });
//
//        mvc.perform(MockMvcRequestBuilders
//                        .get("/products/{id}", dummyProduct.getId())
//                        .header(HttpHeaders.AUTHORIZATION, AuthorizationSchemas.BEARER + " " + dummyToken)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(dummyProduct.getId().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(dummyProduct.getName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.price").doesNotExist());
//
//        ArgumentCaptor<UUID> productArgumentCaptor = ArgumentCaptor.forClass(UUID.class);
//        verify(productService, times(1)).findById(productArgumentCaptor.capture());
//        assertThat(productArgumentCaptor.getValue()).isEqualTo(dummyProduct.getId());
//    }
//
//    @Test
//    public void create_requestProductDTOToBeCreated_expectCreatedProductAsDTO() throws Exception {
//        UUID uuid = UUID.randomUUID();
//
//        given(userService.findById(any(UUID.class))).willReturn(new User());
//        given(productService.save(any(Product.class))).will(invocation -> {
//            if ("non-existent".equals(invocation.getArgument(0))) throw new Exception("Product could not be created");
//            return ((Product) invocation.getArgument(0)).setPurchasePrice(dummyProduct.getPurchasePrice()).setId(uuid);
//        });
//
//        mvc.perform(MockMvcRequestBuilders
//                        .post("/products")
//                        .header(HttpHeaders.AUTHORIZATION, AuthorizationSchemas.BEARER + " " + dummyToken)
//                        .content(new ObjectMapper().writeValueAsString(dummyProduct.setId(null)))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(uuid.toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(dummyProduct.getName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.price").doesNotExist());
//
//        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
//        verify(productService, times(1)).save(productArgumentCaptor.capture());
//        assertThat(productArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(dummyProduct.setId(uuid));
//    }
//
//    @Test
//    public void updateProduct_requestProductDTOToBeUpdated_expectUpdatedProductDTO() throws Exception {
//        String updatedProductName = "updatedProductName";
//
//        given(userService.findById(any(UUID.class))).willReturn(new User());
//        given(productService.save(any(Product.class))).will(invocation -> {
//            if ("non-existent".equals(invocation.getArgument(0))) throw new Exception("Product could not be updated");
//            return ((Product) invocation.getArgument(0)).setName(updatedProductName);
//        });
//
//        mvc.perform(MockMvcRequestBuilders
//                        .put("/products")
//                        .header(HttpHeaders.AUTHORIZATION, AuthorizationSchemas.BEARER + " " + dummyToken)
//                        .content(new ObjectMapper().writeValueAsString(dummyProduct))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(dummyProduct.getId().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(updatedProductName))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.price").doesNotExist());
//
//        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
//        verify(productService, times(1)).save(productArgumentCaptor.capture());
//        assertAll(
//                () -> assertThat(productArgumentCaptor.getValue().getId()).isEqualTo(dummyProduct.getId()),
//                () -> assertThat(productArgumentCaptor.getValue().getName()).isEqualTo(updatedProductName),
//                () -> assertThat(productArgumentCaptor.getValue().getPurchasePrice()).isNull()
//        );
//    }
//
//    @Test
//    public void deleteProductById_requestDeletionOfProductById_expectAppropriateState() throws Exception {
//        given(userService.findById(any(UUID.class))).willReturn(new User());
//        given(productService.deleteById(any(UUID.class))).will(invocation -> {
//            if ("non-existent".equals(invocation.getArgument(0)))
//                throw new NoSuchElementException("No such product present");
//            return null;
//        });
//
//        mvc.perform(MockMvcRequestBuilders
//                        .delete("/products/{id}", dummyProduct.getId())
//                        .header(HttpHeaders.AUTHORIZATION, AuthorizationSchemas.BEARER + " " + dummyToken)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//
//        ArgumentCaptor<UUID> productArgumentCaptor = ArgumentCaptor.forClass(UUID.class);
//        verify(productService, times(1)).deleteById(productArgumentCaptor.capture());
//        assertThat(productArgumentCaptor.getValue()).isEqualTo(dummyProduct.getId());
//    }
//}
