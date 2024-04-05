package by.dto.product_dto;

import by.database.entity.Category;
import by.database.entity.Supplier;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductDto {
    Long id;
    Supplier supplier;
    String name;
    Long count;
    Long priceForOne;
    Category category;
}
