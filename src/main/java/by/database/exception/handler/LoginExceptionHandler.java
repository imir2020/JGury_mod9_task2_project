package by.database.exception.handler;


import by.database.exception.exception.UserLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class LoginExceptionHandler {
    @ExceptionHandler(UserLoginException.class)
    public String handlerUserLoginException(Exception e) {
        log.error("Failed attempt to login ", e);
        return "login/user_registration";
    }
}
