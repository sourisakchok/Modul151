package com.example.jwt.domain.category;

import com.example.jwt.core.generic.ExtendedEntity;
import com.example.jwt.domain.product.Product;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends ExtendedEntity {

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "product_category",
      joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
  )
  private Set<Product> products = new HashSet<>();

  public Category() {

  }

  public Category(String name, Set<Product> products) {
    this.name = name;
    this.products = products;
  }

  public Category(UUID id, String name, Set<Product> products) {
    super(id);
    this.name = name;
    this.products = products;
  }

  public String getName() {
    return name;
  }

  public Category setName(String name) {
    this.name = name;
    return this;
  }

  public Set<Product> getProducts() {
    return products;
  }

  public Category setProducts(Set<Product> products) {
    this.products = products;
    return this;
  }
}
