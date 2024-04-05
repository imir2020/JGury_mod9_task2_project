package by.mapper.classes.products;

import by.database.entity.Product;
import by.dto.product_dto.FromProductDtoToBase;
import by.mapper.map_interfaces.product.DtoToProductMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@NoArgsConstructor
public class DtoToProduct {

    public Product mapFrom(FromProductDtoToBase fromProductDtoToBase){
        log.info("Object of FromProductDtoToBase type will be converted to Product type");
        return DtoToProductMapper.INSTANCE.mapFrom(fromProductDtoToBase);
    }
}
