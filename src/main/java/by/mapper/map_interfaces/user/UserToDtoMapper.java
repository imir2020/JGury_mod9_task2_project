package by.mapper.map_interfaces.user;

import by.database.entity.User;
import by.dto.user_dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserToDtoMapper {
    UserToDtoMapper INSTANCE = Mappers.getMapper(UserToDtoMapper.class);

    //@Mapping(source = "image", target = "image.originalFilename")
    UserDto toDto(User user);
}
