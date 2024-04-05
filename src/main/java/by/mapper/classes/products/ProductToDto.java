package by.mapper.classes.products;


import by.database.entity.Product;
import by.dto.product_dto.ProductDto;
import by.mapper.map_interfaces.product.ProductToDtoMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@NoArgsConstructor
public class ProductToDto {

    public ProductDto mapFrom(Product product){
        log.info("Object of Product type will be converted to ProductDto type");
        return ProductToDtoMapper.INSTANCE.mapFrom(product);
    }
}
