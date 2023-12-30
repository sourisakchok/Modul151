package com.example.integration;
import com.example.jwt.JwtApplication;
import com.example.jwt.core.security.helpers.AuthorizationSchemas;
import com.example.jwt.core.security.helpers.JwtProperties;
import com.example.jwt.domain.authority.Authority;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.product.ProductRepository;
import com.example.jwt.domain.product.ProductService;
import com.example.jwt.domain.role.Role;
import com.example.jwt.domain.role.RoleEnum;
import com.example.jwt.domain.role.RoleRepository;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.UserRepository;
import com.example.jwt.domain.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = JwtApplication.class)
@ContextConfiguration
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JwtProperties jwtProperties;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private ProductService productService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private ProductRepository productRepository;
    private Product dummyProduct;
    private String dummyToken;

    private String generateToken() {
        String bla = Decoders.BASE64.decode(jwtProperties.getSecret()).toString();
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());

        return Jwts.builder()
                .setClaims(Map.of("sub", UUID.randomUUID().toString()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMillis()))
                .setIssuer(jwtProperties.getIssuer())
                .signWith(Keys.hmacShaKeyFor(keyBytes))
                .compact();
    }

    @BeforeEach
    public void setUp() {
        dummyToken = generateToken();
        Role role = new Role();
        role.setName(RoleEnum.valueOf("CLIENT"));
        roleRepository.saveAndFlush(role);
        dummyProduct = new Product(UUID.randomUUID(), "kettle", null, null, 0, 107, null, null);
        User user = new User();
        user.setEmail("john@doe.com");
        user.setId(UUID.randomUUID());
        user.setRole(role);
        userRepository.saveAndFlush(user);
    }


    @Test
    public void retrieveAll_requestAllProducts_expectAllProductsAsDTOS() throws Exception {
        List<Product> dummyProducts = Stream.of(
                new Product(UUID.randomUUID(), "shirt", null, null, 0, 49, null, null),
                new Product(UUID.randomUUID(), "sandwich", null, null, 0, 8, null, null)
        ).collect(Collectors.toList());


        // Define the user variable
        User user = new User();
        user.setEmail("john@doe.com");
        user.setRole(new Role().setAuthorities(Set.of(new Authority().setName("CAN_RETRIEVE_PRODUCTS"))));

        given(userService.findById(any(UUID.class))).willReturn(user);
        given(productService.findAll()).willReturn(dummyProducts);
        mvc.perform(MockMvcRequestBuilders
                        .get("/products")
                        .header(HttpHeaders.AUTHORIZATION, AuthorizationSchemas.BEARER + " " + dummyToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").value(Matchers.containsInAnyOrder(dummyProducts.get(0).getId().toString(), dummyProducts.get(1).getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name").value(Matchers.containsInAnyOrder(dummyProducts.get(0).getName(), dummyProducts.get(1).getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].price").doesNotExist());
    }
//
//    @Test
//    public void retrieveById_requestProductById_expectProductAsDTO() throws Exception {
//        User user = new User();
//        user.setEmail("john@doe.com");
//        user.setRole(new Role().setName(RoleEnum.valueOf("CLIENT")));
//
//        Product product = new Product(UUID.randomUUID(),"sandwich", null, null, 0, 8, null, null);
//
//        mvc.perform(MockMvcRequestBuilders
//                        .get("/products/{id}", product.getId())
//                        .header(HttpHeaders.AUTHORIZATION, AuthorizationSchemas.BEARER + " " + dummyToken)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(product.getId()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(product.getName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(product.getSalePrice()));
//    }

//    @Test
//    @Disabled("Not implemented yet")
//    public void create_requestProductDTOToBeCreated_expectCreatedProductAsDTO() throws Exception {}
//
//    @Test
//    @Disabled("Not implemented yet")
//    public void updateProduct_requestProductDTOToBeUpdated_expectUpdatedProductDTO() throws Exception {}
//
//    @Test
//    @Disabled("Not implemented yet")
//    public void deleteProductById_requestDeletionOfProductById_expectAppropriateState() throws Exception {}
}
