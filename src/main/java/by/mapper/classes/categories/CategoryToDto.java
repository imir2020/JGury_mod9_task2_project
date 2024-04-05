package by.mapper.classes.categories;


import by.database.entity.Category;
import by.dto.category_dto.CategoryDto;
import by.mapper.map_interfaces.category.CategoryToDtoMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@NoArgsConstructor
public class CategoryToDto {

    public CategoryDto mapFrom(Category category){
        log.info("Object of Category type will be converted to CategoryDto type");
        return CategoryToDtoMapper.INSTANCE.fromCategory(category);
    }
}
