package com.example.integration;
import com.example.jwt.JwtApplication;
import com.example.jwt.core.security.helpers.AuthorizationSchemas;
import com.example.jwt.core.security.helpers.JwtProperties;
import com.example.jwt.domain.authority.Authority;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.product.ProductRepository;
import com.example.jwt.domain.role.Role;
import com.example.jwt.domain.role.RoleEnum;
import com.example.jwt.domain.role.RoleRepository;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.UserRepository;
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
    private UserRepository userRepository;

    @MockBean
    private ProductRepository productRepository;

    private String generateToken(UUID subject) {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());

        return Jwts.builder()
                .setClaims(Map.of("sub", subject))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMillis()))
                .setIssuer(jwtProperties.getIssuer())
                .signWith(Keys.hmacShaKeyFor(keyBytes))
                .compact();
    }

    @BeforeEach
    public void setUp() {
        Role role = new Role();
        role.setName(RoleEnum.valueOf("CLIENT"));
        roleRepository.saveAndFlush(role);

        User user = new User();
        user.setEmail("john@doe.com");
        user.setRole(role);
        userRepository.saveAndFlush(user);
    }

    @Test
    public void retrieveAll_requestAllProducts_expectAllProductsAsDTOS() throws Exception {
        List<Product> dummyProducts = productRepository.saveAllAndFlush(
                Stream.of(
                        new Product("shirt", null, null, 0, 49, null, null),
                        new Product("sandwich", null, null, 0, 8, null, null)
                ).collect(Collectors.toList())
        );

        // Define the user variable
        User user;

        // Initialize the user variable
        user = userRepository.findByEmail("john@doe.com").get();

        mvc.perform(MockMvcRequestBuilders
                        .get("/products")
                        .header(HttpHeaders.AUTHORIZATION, AuthorizationSchemas.BEARER + " " + generateToken(user.getId()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").value(Matchers.containsInAnyOrder(dummyProducts.get(0).getId().toString(), dummyProducts.get(1).getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name").value(Matchers.containsInAnyOrder(dummyProducts.get(0).getName(), dummyProducts.get(1).getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].price").value(Matchers.containsInAnyOrder(dummyProducts.get(0).getPurchasePrice(), dummyProducts.get(1).getPurchasePrice())));
    }

    @Test
    public void retrieveById_requestProductById_expectProductAsDTO() throws Exception {
        User user = new User();
        user.setEmail("john@doe.com");
        user.setRole(new Role().setName(RoleEnum.valueOf("CLIENT")));

        Product product = productRepository.saveAndFlush(new Product("sandwich", null, null, 0, 8, null, null));

        mvc.perform(MockMvcRequestBuilders
                        .get("/products/{id}", product.getId())
                        .header(HttpHeaders.AUTHORIZATION, AuthorizationSchemas.BEARER + " " + generateToken(user.getId()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(product.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(product.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(product.getSalePrice()));
    }

    @Test
    @Disabled("Not implemented yet")
    public void create_requestProductDTOToBeCreated_expectCreatedProductAsDTO() throws Exception {}

    @Test
    @Disabled("Not implemented yet")
    public void updateProduct_requestProductDTOToBeUpdated_expectUpdatedProductDTO() throws Exception {}

    @Test
    @Disabled("Not implemented yet")
    public void deleteProductById_requestDeletionOfProductById_expectAppropriateState() throws Exception {}
}
