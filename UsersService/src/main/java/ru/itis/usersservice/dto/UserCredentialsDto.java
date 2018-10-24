package ru.itis.usersservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Bulat Giniyatullin
 * 24 October 2018
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialsDto implements Serializable {
    private String email;
    private String password;
}
