package by.http.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice(basePackages = "by.database.http.controller")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
}
