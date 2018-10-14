package ru.itis.usersservice.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.usersservice.models.User;
import ru.itis.usersservice.repositories.UserRepository;
import ru.itis.usersservice.services.interfaces.UsersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Bulat Giniyatullin
 * 14 October 2018
 */

@Service
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
