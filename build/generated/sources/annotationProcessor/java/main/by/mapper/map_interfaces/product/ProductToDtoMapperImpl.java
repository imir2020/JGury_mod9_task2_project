package by.mapper.map_interfaces.product;

import by.database.entity.Product;
import by.dto.product_dto.ProductDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-05T19:10:53+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
public class ProductToDtoMapperImpl implements ProductToDtoMapper {

    @Override
    public ProductDto mapFrom(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.id( product.getId() );
        productDto.supplier( product.getSupplier() );
        productDto.name( product.getName() );
        productDto.count( product.getCount() );
        productDto.priceForOne( product.getPriceForOne() );
        productDto.category( product.getCategory() );

        return productDto.build();
    }
}
