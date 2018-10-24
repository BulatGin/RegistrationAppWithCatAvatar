package ru.itis.uiserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.itis.uiserver.dto.TokenDto;
import ru.itis.uiserver.dto.UserCredentialsDto;

/**
 * @author Bulat Giniyatullin
 * 24 October 2018
 */

@RestController
public class AuthController {
    @Autowired
    RestTemplate restTemplate;

    @Value("${api.auth}")
    private String authUrl;

    @PostMapping("/auth")
    public TokenDto authenticate(@ModelAttribute UserCredentialsDto userCredentials) {
        return restTemplate.exchange(
                authUrl,
                HttpMethod.POST,
                new HttpEntity<>(userCredentials, new HttpHeaders()),
                TokenDto.class).getBody();
    }
}
