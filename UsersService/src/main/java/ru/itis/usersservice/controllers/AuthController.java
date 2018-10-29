package ru.itis.usersservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.usersservice.dto.TokenDto;
import ru.itis.usersservice.dto.UserCredentialsDto;
import ru.itis.usersservice.services.interfaces.AuthService;

/**
 * @author Bulat Giniyatullin
 * 24 October 2018
 */

@RestController
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/auth")
    public TokenDto login(@RequestBody UserCredentialsDto userCredentials) {
        return authService.authorize(userCredentials);
    }
}
