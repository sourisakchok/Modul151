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
    date = "2023-12-13T21:55:38+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.36.0.v20231114-0937, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category fromDTO(CategoryDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( arg0.getId() );
        category.setName( arg0.getName() );

        return category;
    }

    @Override
    public List<Category> fromDTOs(List<CategoryDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( arg0.size() );
        for ( CategoryDTO categoryDTO : arg0 ) {
            list.add( fromDTO( categoryDTO ) );
        }

        return list;
    }

    @Override
    public Set<Category> fromDTOs(Set<CategoryDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Set<Category> set = new LinkedHashSet<Category>( Math.max( (int) ( arg0.size() / .75f ) + 1, 16 ) );
        for ( CategoryDTO categoryDTO : arg0 ) {
            set.add( fromDTO( categoryDTO ) );
        }

        return set;
    }

    @Override
    public CategoryDTO toDTO(Category arg0) {
        if ( arg0 == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( arg0.getId() );
        categoryDTO.setName( arg0.getName() );

        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> toDTOs(List<Category> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<CategoryDTO> list = new ArrayList<CategoryDTO>( arg0.size() );
        for ( Category category : arg0 ) {
            list.add( toDTO( category ) );
        }

        return list;
    }

    @Override
    public Set<CategoryDTO> toDTOs(Set<Category> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Set<CategoryDTO> set = new LinkedHashSet<CategoryDTO>( Math.max( (int) ( arg0.size() / .75f ) + 1, 16 ) );
        for ( Category category : arg0 ) {
            set.add( toDTO( category ) );
        }

        return set;
    }
}
