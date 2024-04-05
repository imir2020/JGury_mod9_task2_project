package by.mapper.map_interfaces.product;


import by.database.entity.Product;
import by.dto.product_dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductToDtoMapper {

    ProductToDtoMapper INSTANCE = Mappers.getMapper(ProductToDtoMapper.class);

    ProductDto mapFrom(Product product);
}
