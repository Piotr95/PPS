package szczepaniak.ppss.AuthorizationService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import szczepaniak.ppss.AuthorizationService.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void register(@RequestParam String username, @RequestParam String password) {
        userService.create(username, password);
    }
}
