package database.integration.service;


import annotation.IT;
import by.database.entity.UserStatus;
import by.dto.user_dto.FromDtoToUser;
import by.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
public class UserServiceIT {
    private final UserService userService;

    @Test
    public  void findLogin(){
        String password = "111";
        var result = userService.login(password);
        assertTrue(result.isPresent());
    }

    @Test
    public void save(){
//        FromDtoToUser user = FromDtoToUser.builder()
//                .name("Ron")
//                .birthday("1990-01-24")
//                .password("34er67")
//                .status(String.valueOf(UserStatus.ADMIN))
//                .build();
//        userService.save(user);
    }

    @Test
    public void delete(){
        Long id = 2L;
        userService.delete(id);
        var checkResult = userService.findById(id);
        assertTrue(checkResult.isEmpty());
    }
}
