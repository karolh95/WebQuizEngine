package engine.controllers;

import engine.models.User;
import engine.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService service;

    @PostMapping("register")
    public User registerUser(@Valid @RequestBody User user) {
        return service.createUser(user);
    }

    @GetMapping("login")
    public User user(@AuthenticationPrincipal UserDetails userDetails) {
        return service.getUserByUsername(userDetails.getUsername());
    }
}
