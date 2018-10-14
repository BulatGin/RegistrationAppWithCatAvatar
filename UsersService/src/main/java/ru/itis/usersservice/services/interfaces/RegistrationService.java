package ru.itis.usersservice.services.interfaces;

import ru.itis.usersservice.dto.UserDto;

/**
 * @author Bulat Giniyatullin
 * 14 October 2018
 */

public interface RegistrationService {
    void register(UserDto user);
}
