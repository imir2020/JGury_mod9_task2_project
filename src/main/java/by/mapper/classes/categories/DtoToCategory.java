package by.mapper.classes.categories;


import by.database.entity.Category;
import by.dto.category_dto.FromDtoToCategory;
import by.mapper.map_interfaces.category.DtoToCategoryMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@NoArgsConstructor
public class DtoToCategory {

    public Category mapFrom(FromDtoToCategory fromCategoryDtoToCategory){
        log.info("Object of CategoryDto type will be converted to Category type");
        return DtoToCategoryMapper.INSTANCE.fromDto(fromCategoryDtoToCategory);
    }
}
