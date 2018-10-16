package ru.itis.usersservice.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.usersservice.dto.UserDto;
import ru.itis.usersservice.models.User;
import ru.itis.usersservice.repositories.UserRepository;
import ru.itis.usersservice.services.interfaces.RegistrationService;

import javax.transaction.Transactional;

/**
 * @author Bulat Giniyatullin
 * 14 October 2018
 */

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void register(UserDto userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .avatar(userDto.getAvatar())
                .build();
        userRepository.save(user);
    }
}
