package by.http.restcontroller;


import by.database.entity.UserStatus;
import by.dto.login.LoginDto;
import by.dto.user_dto.UserDto;
import by.http.handler.RestControllerExceptionHandler;
import by.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginRestController {

    private final UserService userService;

    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> login(LoginDto loginDto) {
        return userService.login(loginDto.getPassword())
                .map(ResponseEntity::ok)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}


