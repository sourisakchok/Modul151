package com.example.jwt.domain.product;

import com.example.jwt.core.generic.ExtendedEntity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="product")
public class Product extends ExtendedEntity {
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

  public Product(String name, String originCountry, double purchasePrice, double salePrice, Date harvestDate) {
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