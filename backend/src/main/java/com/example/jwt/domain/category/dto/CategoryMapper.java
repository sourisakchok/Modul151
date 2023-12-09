package com.example.jwt.domain.category.dto;

import com.example.jwt.core.generic.ExtendedMapper;
import com.example.jwt.domain.category.Category;
import com.example.jwt.domain.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper extends ExtendedMapper<Category, CategoryDTO> {

}
