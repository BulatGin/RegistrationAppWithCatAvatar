package ru.itis.usersservice.services.interfaces;

import ru.itis.usersservice.models.User;

import java.util.List;

/**
 * @author Bulat Giniyatullin
 * 14 October 2018
 */

public interface UsersService {
    List<User> getAll();

    void banUser(Long userId);
}
