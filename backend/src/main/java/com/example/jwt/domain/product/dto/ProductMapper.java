package com.example.jwt.domain.product.dto;

import com.example.jwt.domain.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

  Product productDTOToProduct(ProductDTO productDTO);
}
