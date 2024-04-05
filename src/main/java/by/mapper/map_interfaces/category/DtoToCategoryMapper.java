package by.mapper.map_interfaces.category;


import by.database.entity.Category;
import by.dto.category_dto.FromDtoToCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoToCategoryMapper {
    DtoToCategoryMapper INSTANCE = Mappers.getMapper(DtoToCategoryMapper.class);
            Category fromDto(FromDtoToCategory dtoToCategory);
}
