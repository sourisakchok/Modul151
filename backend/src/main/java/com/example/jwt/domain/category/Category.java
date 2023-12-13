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

  // @ManyToMany(fetch = FetchType.EAGER)
  // @JoinTable(
  // name = "product_category",
  // joinColumns = @JoinColumn(name = "_id", referencedColumnName = "id"),
  // inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName =
  // "id")
  // )
  // private Set<Product> authorities = new HashSet<>();

  public Category() {
  }

  public Category(UUID id, String name, Set<Product> authorities) {
    super(id);
    this.name = name;
    // this.authorities = authorities;
  }

  public String getName() {
    return name;
  }

  public Category setName(String name) {
    this.name = name;
    return this;
  }

  // public Set<Product> getProduct() {
  // return authorities;
  // }

  // public Category setProduct(Set<Product> authorities) {
  // this.authorities = authorities;
  // return this;
  // }
}
