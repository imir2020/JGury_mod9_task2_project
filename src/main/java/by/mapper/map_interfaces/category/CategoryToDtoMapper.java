package by.mapper.map_interfaces.category;


import by.database.entity.Category;
import by.dto.category_dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryToDtoMapper {
    CategoryToDtoMapper INSTANCE = Mappers.getMapper(CategoryToDtoMapper.class);

    //@Mapping(source = "category.id", target = "category")
    CategoryDto fromCategory(Category category);

}
