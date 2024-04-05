package by.dto.category_dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CategoryDto{
        Long id;
        String categoryName;

}
