package ru.itis.uiserver.controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.itis.uiserver.dto.AvatarResponseDto;

/**
 * @author Bulat Giniyatullin
 * 15 October 2018
 */

@Controller
public class AvatarController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @MessageMapping("/avatar")
    public void avatarRequest(@Header("simpSessionId") String sessionId) {
        rabbitTemplate.convertAndSend("avatar-request", sessionId);
    }

    @RabbitListener(queues = "avatar-responses")
    public void avatarResponse(AvatarResponseDto avatarResponseDto) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(avatarResponseDto.getUserId());
        headerAccessor.setLeaveMutable(true);

        simpMessagingTemplate.convertAndSendToUser(avatarResponseDto.getUserId(),
                "/queue/avatar",
                avatarResponseDto.getCatUrl(),
                headerAccessor.getMessageHeaders());
    }
}
