package by.mapper.classes.users;

import by.database.entity.User;
import by.dto.user_dto.FromDtoToUser;
import by.mapper.map_interfaces.user.DtoToUserMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@NoArgsConstructor
public class DtoToUser {

    public User mapFrom(FromDtoToUser userDto) {
        log.info("Object of FromUserDtoToBase type will be converted to User type");
        return DtoToUserMapper.INSTANCE.mapFrom(userDto);
    }

}
