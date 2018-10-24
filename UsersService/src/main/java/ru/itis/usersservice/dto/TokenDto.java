package ru.itis.usersservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bulat Giniyatullin
 * 24 October 2018
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private boolean isSuccess;
    private String token;
    private String errorMsg;
}
