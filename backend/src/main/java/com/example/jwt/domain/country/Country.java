package com.example.jwt.domain.country;

import com.example.jwt.core.generic.ExtendedEntity;
import com.example.jwt.domain.product.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "country")
public class Country extends ExtendedEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy="originCountry")
    private Set<Product> products;

    public Country() {

    }
    public Country(String name, Set<Product> products) {
        this.name = name;
        this.products = products;
    }

    public Country(UUID id, String name, Set<Product> products) {
        super(id);
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Country setProducts(Set<Product> products) {
        this.products = products;
        return this;
    }
}
