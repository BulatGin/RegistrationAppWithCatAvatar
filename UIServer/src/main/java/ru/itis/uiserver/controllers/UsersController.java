package ru.itis.uiserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author Bulat Giniyatullin
 * 17 October 2018
 */

@Controller
public class UsersController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.users}")
    String usersApiUrl;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public String getAllUsers() {
        String usersJson = restTemplate.getForEntity(usersApiUrl, String.class).getBody();
        return usersJson;
    }
}
