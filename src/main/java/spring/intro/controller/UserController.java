package spring.intro.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.intro.dto.UserResponseDto;
import spring.intro.model.User;
import spring.intro.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    public String inject() {
        User bob = new User("Bob", "bob@i.ua", "1234");
        User alice = new User("Alice", "alice@mail.com", "1234");
        userService.add(bob);
        userService.add(alice);
        return "Users were injected";
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        User user = userService.getById(id);
        return getUserDto(user);
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        List<User> listOfAllUsers = userService.getAllUsers();
        return listOfAllUsers.stream()
                .map(UserController::getUserDto)
                .collect(Collectors.toList());
    }

    private static UserResponseDto getUserDto(User user) {
        UserResponseDto userDto = new UserResponseDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
