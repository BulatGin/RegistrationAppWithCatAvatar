package ru.itis.usersservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.usersservice.models.User;
import ru.itis.usersservice.services.interfaces.UsersService;

import java.util.List;

/**
 * @author Bulat Giniyatullin
 * 14 October 2018
 */

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return usersService.getAll();
    }
}
