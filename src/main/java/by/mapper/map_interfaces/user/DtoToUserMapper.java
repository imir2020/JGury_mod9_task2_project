package by.mapper.map_interfaces.user;

import by.database.entity.User;
import by.dto.user_dto.FromDtoToUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoToUserMapper {
    DtoToUserMapper INSTANCE = Mappers.getMapper(DtoToUserMapper.class);

    @Mapping(source = "image.originalFilename", target = "image")
    User mapFrom(FromDtoToUser userDto);
}
