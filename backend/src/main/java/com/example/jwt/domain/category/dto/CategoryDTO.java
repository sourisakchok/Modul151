package com.example.jwt.domain.category.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.product.dto.ProductDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

public class CategoryDTO extends ExtendedDTO {

  @NotNull
  @Size(min = 1, max = 255)
  private String name;

  @Valid
  private Set<ProductDTO> authorities;

  public CategoryDTO() {
  }

  public CategoryDTO(UUID id, String name, Set<ProductDTO> authorities) {
    super(id);
    this.name = name;
    this.authorities = authorities;
  }

  public String getName() {
    return name;
  }

  public CategoryDTO setName(String name) {
    this.name = name;
    return this;
  }

  public Set<ProductDTO> getProduct() {
    return authorities;
  }

  public CategoryDTO setProduct(Set<ProductDTO> authorities) {
    this.authorities = authorities;
    return this;
  }
}