package com.example.jwt.domain.category.dto;

import com.example.jwt.domain.category.Category;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.product.dto.ProductDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-13T23:01:27+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.9 (Eclipse Adoptium)"
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
        category.setProduct( productDTOSetToProductSet( dto.getProduct() ) );

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
        categoryDTO.setProduct( productSetToProductDTOSet( BO.getProduct() ) );

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

    protected Product productDTOToProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( productDTO.getName() );
        product.setOriginCountry( productDTO.getOriginCountry() );
        product.setPurchasePrice( productDTO.getPurchasePrice() );
        product.setSalePrice( productDTO.getSalePrice() );
        product.setHarvestDate( productDTO.getHarvestDate() );

        return product;
    }

    protected Set<Product> productDTOSetToProductSet(Set<ProductDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Product> set1 = new LinkedHashSet<Product>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProductDTO productDTO : set ) {
            set1.add( productDTOToProduct( productDTO ) );
        }

        return set1;
    }

    protected ProductDTO productToProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        String name = null;
        String originCountry = null;
        double purchasePrice = 0.0d;
        double salePrice = 0.0d;
        Date harvestDate = null;

        name = product.getName();
        originCountry = product.getOriginCountry();
        purchasePrice = product.getPurchasePrice();
        salePrice = product.getSalePrice();
        harvestDate = product.getHarvestDate();

        ProductDTO productDTO = new ProductDTO( name, originCountry, purchasePrice, salePrice, harvestDate );

        return productDTO;
    }

    protected Set<ProductDTO> productSetToProductDTOSet(Set<Product> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProductDTO> set1 = new LinkedHashSet<ProductDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Product product : set ) {
            set1.add( productToProductDTO( product ) );
        }

        return set1;
    }
}
