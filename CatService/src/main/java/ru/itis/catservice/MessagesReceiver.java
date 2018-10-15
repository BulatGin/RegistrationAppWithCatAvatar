package ru.itis.catservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.itis.catservice.dto.AvatarResponseDto;
import ru.itis.catservice.dto.CatDto;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Bulat Giniyatullin
 * 11 October 2018
 */

@Component
public class MessagesReceiver {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${api.cat.url}")
    String catApiUrl;

    @Value("${rabbit.exchange}")
    String rabbitExchange;

    private ExecutorService threadPool = Executors.newCachedThreadPool();

    @RabbitListener(queues = "avatar-requests")
    public void return_avatar(String userId) {
        Runnable task = () -> {
            String catUrl = restTemplate.getForEntity(catApiUrl, CatDto[].class)
                    .getBody()[0]
                    .getUrl();
            AvatarResponseDto response = AvatarResponseDto.builder()
                    .userId(userId)
                    .catUrl(catUrl)
                    .build();
            rabbitTemplate.convertAndSend(rabbitExchange, "avatar-response", response);
        };
        threadPool.execute(task);
    }
}
