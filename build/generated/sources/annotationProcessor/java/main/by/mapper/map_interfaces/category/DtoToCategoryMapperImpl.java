package by.mapper.map_interfaces.category;

import by.database.entity.Category;
import by.dto.category_dto.FromDtoToCategory;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-05T19:10:53+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
public class DtoToCategoryMapperImpl implements DtoToCategoryMapper {

    @Override
    public Category fromDto(FromDtoToCategory dtoToCategory) {
        if ( dtoToCategory == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.categoryName( dtoToCategory.getCategoryName() );

        return category.build();
    }
}
