package by.http.restcontroller;


import by.dto.user_dto.FromDtoToUser;
import by.dto.user_dto.UserDto;
import by.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;



@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRegistrationRestController {
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<FromDtoToUser> userRegistrPage(@RequestBody FromDtoToUser fromDtoToUser) {
        return ResponseEntity.ok(fromDtoToUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}/avatar", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findAvatar(@PathVariable("id")Long id){
        return userService.findAvatar(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String create(FromDtoToUser fromDtoToUser) {
        var userId = userService.save(fromDtoToUser);
        System.out.println(userId);
        return "redirect:/users/" + userId;
    }

    @DeleteMapping("/{id}")
    public void delete(FromDtoToUser fromDtoToUser) {
        userService.deleteByPassword(fromDtoToUser.getPassword());
    }
}
