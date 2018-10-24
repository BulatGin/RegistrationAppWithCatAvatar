package ru.itis.usersservice.services.interfaces;

import ru.itis.usersservice.dto.TokenDto;
import ru.itis.usersservice.dto.UserCredentialsDto;

/**
 * @author Bulat Giniyatullin
 * 24 October 2018
 */

public interface AuthService {
    TokenDto authorize(UserCredentialsDto userCredentials);
}
