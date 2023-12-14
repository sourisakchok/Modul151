package com.example.jwt.domain.product.dto;

import com.example.jwt.domain.product.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-13T23:01:27+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product productDTOToProduct(ProductDTO productDTO) {
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
}
