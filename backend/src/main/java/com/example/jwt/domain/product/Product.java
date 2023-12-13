package com.example.jwt.domain.product;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="product")
public class Product {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "id", updatable = false, nullable = false, unique = true)
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "originCountry")
  private String originCountry;

  @Column(name = "purchasePrice")
  private double purchasePrice;

  @Column(name = "salePrice")
  private double salePrice;

  @Column(name = "harvestDate")
  private Date harvestDate;

  public Product() {
  }

  public Product(UUID id, String name, String originCountry, double purchasePrice, double salePrice, Date harvestDate) {
    this.id = id;
    this.name = name;
    this.originCountry = originCountry;
    this.purchasePrice = purchasePrice;
    this.salePrice = salePrice;
    this.harvestDate = harvestDate;
  }

  public UUID getId() {
    return id;
  }

  public Product setId(UUID id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Product setName(String name) {
    this.name = name;
    return this;
  }

  public String getOriginCountry() {
    return originCountry;
  }

  public Product setOriginCountry(String originCountry) {
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

  public Date getHarvestDate() {
    return harvestDate;
  }

  public Product setHarvestDate(Date harvestDate) {
    this.harvestDate = harvestDate;
    return this;
  }
}