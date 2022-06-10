package by.store.controllers;

import by.store.Constants;
import by.store.model.User;
import by.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/error")
    public String accessDenied() {
        return "error";
    }

    @GetMapping(value = {"/", "/login"})
    public String home() {
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping(value = "/appRegistration")
    public String registrationUser(Model model,
                                      @RequestParam String username,
                                      @RequestParam String password) {
        User user = new User(username, password);
        boolean flag = userService.save(user);
        if (flag) {
            model.addAttribute(Constants.MESSAGE, Constants.YOU_ARE_REGISTERED);
            return "login";
        }
        model.addAttribute(Constants.MESSAGE, Constants.USER_EXISTS);
        return "registration";
    }
}
