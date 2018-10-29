package ru.itis.usersservice.services.implementations;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.usersservice.dto.TokenDto;
import ru.itis.usersservice.dto.UserCredentialsDto;
import ru.itis.usersservice.models.User;
import ru.itis.usersservice.repositories.UserRepository;
import ru.itis.usersservice.services.interfaces.AuthService;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

/**
 * @author Bulat Giniyatullin
 * 24 October 2018
 */

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public TokenDto authorize(UserCredentialsDto userCredentials) {
        String email = userCredentials.getEmail();
        String password = userCredentials.getPassword();

        boolean success;
        String errorMsg;
        String token;

        Optional<User> optionalUser = userRepository.findOneByEmail(email);
        if (!optionalUser.isPresent()) {
            token = null;
            errorMsg = "User not found";
            success = false;
        } else {
            User user = optionalUser.get();
            if (!user.getPassword().equals(password)) {
                token = null;
                errorMsg = "Wrong credentials";
                success = false;
            } else if (user.isBanned()) {
                token = null;
                errorMsg = "User banned";
                success = false;
            } else {
                Long now = System.currentTimeMillis();
                token = Jwts.builder()
                        .setSubject(String.valueOf(user.getId()))
                        .claim("roles", Collections.singletonList(user.getRole()))
                        .setIssuedAt(new Date(now))
                        .setExpiration(new Date(now + jwtExpiration * 1000))
                        .signWith(SignatureAlgorithm.HS512, jwtSecret)
                        .compact();
                success = true;
                errorMsg = null;
            }
        }

        return TokenDto.builder()
                .token(token)
                .isSuccess(success)
                .errorMsg(errorMsg)
                .build();
    }
}
