package com.example.jwt.domain.product;

import com.example.jwt.core.generic.ExtendedEntity;
import com.example.jwt.domain.country.Country;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="product")
public class Product extends ExtendedEntity {
  @Column(name = "name", nullable = false)
  private String name;
  @ManyToOne
  @JoinColumn(name="country_id", nullable=false)
  private Country originCountry;

  @Column(name = "purchasePrice")
  private double purchasePrice;

  @Column(name = "salePrice")
  private double salePrice;

  @Column(name = "harvestDate")
  private LocalDate harvestDate;

  public Product() {
  }

  public Product(String name, Country originCountry, double purchasePrice, double salePrice, LocalDate harvestDate) {
    this.name = name;
    this.originCountry = originCountry;
    this.purchasePrice = purchasePrice;
    this.salePrice = salePrice;
    this.harvestDate = harvestDate;
  }

  public Product(UUID id, String name, Country originCountry, double purchasePrice, double salePrice, LocalDate harvestDate) {
    super(id);
    this.name = name;
    this.originCountry = originCountry;
    this.purchasePrice = purchasePrice;
    this.salePrice = salePrice;
    this.harvestDate = harvestDate;
  }

  public String getName() {
    return name;
  }

  public Product setName(String name) {
    this.name = name;
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
}