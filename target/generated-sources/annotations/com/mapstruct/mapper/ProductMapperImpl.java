package com.mapstruct.mapper;

import com.mapstruct.dto.ProductDTO;
import com.mapstruct.entity.Product;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-11T14:11:48+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product dtoToProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setDesc( productDTO.getDescription() );
        product.setId( productDTO.getId() );
        product.setName( productDTO.getName() );
        product.setQuantity( productDTO.getQuantity() );
        product.setPrice( productDTO.getPrice() );

        return product;
    }

    @Override
    public ProductDTO productToDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setDescription( product.getDesc() );
        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setQuantity( product.getQuantity() );
        productDTO.setPrice( product.getPrice() );

        return productDTO;
    }
}
