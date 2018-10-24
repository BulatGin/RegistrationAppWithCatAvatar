package ru.itis.uiserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bulat Giniyatullin
 * 24 October 2018
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCredentialsDto {
    private String email;
    private String password;
}
