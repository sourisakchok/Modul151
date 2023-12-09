package com.example.jwt.domain.product.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class ProductDTO {

  @NotNull
  private String name;
  @Max(100)
  private int price;

  public ProductDTO(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public ProductDTO setName(String name) {
    this.name = name;
    return this;
  }

  public int getPrice() {
    return price;
  }

  public ProductDTO setPrice(int price) {
    this.price = price;
    return this;
  }
}
