package ru.itis.uiserver.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import ru.itis.uiserver.services.interfaces.UserService;

/**
 * @author Bulat Giniyatullin
 * 17 October 2018
 */

@Controller
public class UsersController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UserService userService;

    @Value("${api.users}")
    private String usersApiUrl;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public String getAllUsers() {
        String usersJson = restTemplate.getForEntity(usersApiUrl, String.class).getBody();
        return usersJson;
    }

    @PostMapping("/ban")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public String banUser(@RequestParam("id") Long userId) {
        rabbitTemplate.convertAndSend("ban_user", userId);
        userService.addUserToBlackList(userId);
        return "Success";
    }
}
