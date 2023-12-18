package com.example.jwt.domain.product;

import com.example.jwt.core.generic.ExtendedEntity;
import com.example.jwt.domain.category.Category;
import com.example.jwt.domain.country.Country;
import com.example.jwt.domain.order.Order;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="product")
public class Product extends ExtendedEntity {
  @Column(name = "name", nullable = false)
  private String name;

  @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
  private Set<Category> category = new HashSet<>();

  @ManyToOne()
  @JoinColumn(name="country_id", nullable=false)
  private Country originCountry;

  @Column(name = "purchasePrice")
  private double purchasePrice;

  @Column(name = "salePrice")
  private double salePrice;

  @Column(name = "harvestDate")
  private LocalDate harvestDate;

  @OneToMany(mappedBy = "product")
  private Set<Order> orders;

  public Product() {
  }

  public Product(String name, Set<Category> category, Country originCountry, double purchasePrice, double salePrice, LocalDate harvestDate, Set<Order> orders) {
    this.name = name;
    this.category = category;
    this.originCountry = originCountry;
    this.purchasePrice = purchasePrice;
    this.salePrice = salePrice;
    this.harvestDate = harvestDate;
    this.orders = orders;
  }

  public Product(UUID id, String name, Set<Category> category, Country originCountry, double purchasePrice, double salePrice, LocalDate harvestDate, Set<Order> orders) {
    super(id);
    this.name = name;
    this.category = category;
    this.originCountry = originCountry;
    this.purchasePrice = purchasePrice;
    this.salePrice = salePrice;
    this.harvestDate = harvestDate;
    this.orders = orders;
  }

  public String getName() {
    return name;
  }

  public Product setName(String name) {
    this.name = name;
    return this;
  }

  public Set<Category> getCategory() {
    return category;
  }

  public Product setCategory(Set<Category> category) {
    this.category = category;
    return this;
  }

  public Country getOriginCountry() {
    return originCountry;
  }

  public Product setOriginCountry(Country originCountry) {
    this.originCountry = originCountry;
    return this;
  }

  public double getPurchasePrice() {
    return purchasePrice;
  }

  public Product setPurchasePrice(double purchasePrice) {
    this.purchasePrice = purchasePrice;
    return this;
  }

  public double getSalePrice() {
    return salePrice;
  }

  public Product setSalePrice(double salePrice) {
    this.salePrice = salePrice;
    return this;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public Product setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
    return this;
  }

  public Set<Order> getOrders() {
    return orders;
  }

  public Product setOrders(Set<Order> orders) {
    this.orders = orders;
    return this;
  }
}