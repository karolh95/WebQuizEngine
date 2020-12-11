package engine.controllers;

import engine.models.User;
import engine.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService service;

    @PostMapping
    public User registerUser(@Valid @RequestBody User user) {
        return service.createUser(user);
    }

}
