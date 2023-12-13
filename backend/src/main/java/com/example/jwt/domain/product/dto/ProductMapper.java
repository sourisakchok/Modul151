package com.example.jwt.domain.product.dto;

import com.example.jwt.domain.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

  @Mapping(source = "name", target = "name")
  @Mapping(source = "originCountry", target = "originCountry")
  @Mapping(source = "purchasePrice", target = "purchasePrice")
  @Mapping(source = "salePrice", target = "salePrice")
  @Mapping(source = "harvestDate", target = "harvestDate")
  Product productDTOToProduct(ProductDTO productDTO);
}
