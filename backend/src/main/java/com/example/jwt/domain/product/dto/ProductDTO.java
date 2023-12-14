package com.example.jwt.domain.product.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProductDTO {

  @NotNull
  private String name;
  private String originCountry;
  private double purchasePrice;
  private double salePrice;
  private Date harvestDate;

  public ProductDTO(String name, String originCountry, double purchasePrice, double salePrice, Date harvestDate) {
    this.name = name;
    this.originCountry = originCountry;
    this.purchasePrice = purchasePrice;
    this.salePrice = salePrice;
    this.harvestDate = harvestDate;
  }

  public String getName() {
    return name;
  }

  public ProductDTO setName(String name) {
    this.name = name;
    return this;
  }

  public String getOriginCountry() {
    return originCountry;
  }

  public ProductDTO setOriginCountry(String originCountry) {
    this.originCountry = originCountry;
    return this;
  }

  public double getPurchasePrice() {
    return purchasePrice;
  }

  public ProductDTO setPurchasePrice(double purchasePrice) {
    this.purchasePrice = purchasePrice;
    return this;
  }

  public double getSalePrice() {
    return salePrice;
  }

  public ProductDTO setSalePrice(double salePrice) {
    this.salePrice = salePrice;
    return this;
  }

  public Date getHarvestDate() {
    return harvestDate;
  }

  public ProductDTO setHarvestDate(Date harvestDate) {
    this.harvestDate = harvestDate;
    return this;
  }
}