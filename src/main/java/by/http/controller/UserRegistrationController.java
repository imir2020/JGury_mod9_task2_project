package by.http.controller;


import by.database.entity.UserStatus;
import by.dto.user_dto.FromDtoToUser;
import by.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRegistrationController {
    private final UserService userService;

    @GetMapping("/user")
    public String userRegistrPage(Model model, @ModelAttribute FromDtoToUser fromDtoToUser) {
        model.addAttribute("user", fromDtoToUser);
        model.addAttribute("statusName", UserStatus.values());
        return "user/user_registration";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return userService.findById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    model.addAttribute("userStatus", UserStatus.values());
                    return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/registration")
    public String create(FromDtoToUser fromDtoToUser) {
        var userId = userService.save(fromDtoToUser);
        System.out.println(userId);
        return "redirect:/users/" + userId.getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute @Validated FromDtoToUser fromDtoToUser) {
        return userService.update(id, fromDtoToUser)
                .map(it -> "redirect:/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(FromDtoToUser fromDtoToUser) {
        userService.deleteByPassword(fromDtoToUser.getPassword());
        return "redirect:/login/login";
    }

}
