package com.mapstruct.mapper;

import com.mapstruct.dto.ProductDTO;
import com.mapstruct.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "productDTO.description", target = "desc")
    Product dtoToProduct(ProductDTO productDTO);
    @Mapping(source = "product.desc", target = "description")
    ProductDTO productToDto(Product product);
}
