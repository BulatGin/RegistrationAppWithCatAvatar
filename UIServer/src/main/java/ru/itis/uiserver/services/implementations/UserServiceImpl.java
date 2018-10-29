package ru.itis.uiserver.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import ru.itis.uiserver.services.interfaces.UserService;

import java.util.concurrent.TimeUnit;

/**
 * @author Bulat Giniyatullin
 * 24 October 2018
 */

@Service
public class UserServiceImpl implements UserService {
    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private ValueOperations<String, String> valueOperations;

    @Override
    public void addUserToBlackList(Long userId) {
        valueOperations = redisTemplate.opsForValue();
        valueOperations.set(
                String.valueOf(userId),
                String.valueOf(jwtExpiration),
                jwtExpiration,
                TimeUnit.SECONDS);
    }
}
