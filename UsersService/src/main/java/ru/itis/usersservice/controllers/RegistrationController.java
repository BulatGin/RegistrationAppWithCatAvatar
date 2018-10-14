package ru.itis.usersservice.controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.itis.usersservice.dto.RegistrationResponse;
import ru.itis.usersservice.dto.UserDto;
import ru.itis.usersservice.services.interfaces.RegistrationService;

/**
 * @author Bulat Giniyatullin
 * 14 October 2018
 */

@Controller
public class RegistrationController {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RabbitListener(queues = "registration-requests")
    public RegistrationResponse registerUser(UserDto userDto) {
        try {
            registrationService.register(userDto);
            return new RegistrationResponse(true);
        } catch (Exception e) {
            return new RegistrationResponse(false);
        }
    }
}
