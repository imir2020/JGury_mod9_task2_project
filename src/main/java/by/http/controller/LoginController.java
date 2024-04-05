package by.http.controller;


import by.database.entity.UserStatus;
import by.database.exception.exception.UserLoginException;
import by.dto.login.LoginDto;
import by.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "login/login";
    }

    @PostMapping("/login")
    public String login(Model model, LoginDto loginDto) {
        var result = userService.login(loginDto.getPassword());
        if (result.isEmpty()) {
            log.warn("The password is not exist");
            //throw new UserLoginException();
            String error = "password is not valid";
            model.addAttribute("error", error);
            return "redirect:/login";
        }

        model.addAttribute("user", result);
        model.addAttribute("status", UserStatus.values());

        if (result.get().getStatus() == UserStatus.MANAGER) {
            return "redirect:/employees/" + result.get().getId();
        } else {
            return "redirect:/employees";
        }
    }
}


