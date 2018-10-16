package ru.itis.uiserver.controllers;

import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFutureCallback;
import ru.itis.uiserver.dto.RegistrationResponseDto;
import ru.itis.uiserver.dto.UserDto;

/**
 * @author Bulat Giniyatullin
 * 15 October 2018
 */

@Controller
public class RegistrationController {
    @Autowired
    AsyncRabbitTemplate asyncRabbitTemplate;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/register")
    public void registerRequest(@Header("simpSessionId") String sessionId,
                                @Payload UserDto userDto) {
        AsyncRabbitTemplate.RabbitConverterFuture<RegistrationResponseDto> future =
                asyncRabbitTemplate.convertSendAndReceiveAsType("registration-request", userDto,
                        ParameterizedTypeReference.forType(RegistrationResponseDto.class));

        future.addCallback(new ListenableFutureCallback<RegistrationResponseDto>() {
            @Override
            public void onSuccess(RegistrationResponseDto result) {
                SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
                headerAccessor.setSessionId(sessionId);
                headerAccessor.setLeaveMutable(true);

                simpMessagingTemplate.convertAndSendToUser(sessionId,
                        "/queue/registration-response",
                        result.getSuccess(),
                        headerAccessor.getMessageHeaders());
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.print(ex);
            }
        });
    }
}
