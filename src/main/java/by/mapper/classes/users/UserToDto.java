package by.mapper.classes.users;

import by.database.entity.User;
import by.dto.user_dto.UserDto;
import by.mapper.map_interfaces.user.UserToDtoMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@NoArgsConstructor
public class UserToDto {

    public UserDto mapFrom(User user) {
        log.info("Object of User type will be converted to UserDto type");
        return UserToDtoMapper.INSTANCE.toDto(user);
    }
}
