package com.example.jwt.domain.category.dto;

import com.example.jwt.domain.category.Category;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-13T22:07:33+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.36.0.v20231114-0937, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category fromDTO(CategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( dto.getId() );
        category.setName( dto.getName() );

        return category;
    }

    @Override
    public List<Category> fromDTOs(List<CategoryDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( dtos.size() );
        for ( CategoryDTO categoryDTO : dtos ) {
            list.add( fromDTO( categoryDTO ) );
        }

        return list;
    }

    @Override
    public Set<Category> fromDTOs(Set<CategoryDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        Set<Category> set = new LinkedHashSet<Category>( Math.max( (int) ( dtos.size() / .75f ) + 1, 16 ) );
        for ( CategoryDTO categoryDTO : dtos ) {
            set.add( fromDTO( categoryDTO ) );
        }

        return set;
    }

    @Override
    public CategoryDTO toDTO(Category BO) {
        if ( BO == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( BO.getId() );
        categoryDTO.setName( BO.getName() );

        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> toDTOs(List<Category> BOs) {
        if ( BOs == null ) {
            return null;
        }

        List<CategoryDTO> list = new ArrayList<CategoryDTO>( BOs.size() );
        for ( Category category : BOs ) {
            list.add( toDTO( category ) );
        }

        return list;
    }

    @Override
    public Set<CategoryDTO> toDTOs(Set<Category> BOs) {
        if ( BOs == null ) {
            return null;
        }

        Set<CategoryDTO> set = new LinkedHashSet<CategoryDTO>( Math.max( (int) ( BOs.size() / .75f ) + 1, 16 ) );
        for ( Category category : BOs ) {
            set.add( toDTO( category ) );
        }

        return set;
    }
}
