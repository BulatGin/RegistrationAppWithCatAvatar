package ru.itis.usersservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Bulat Giniyatullin
 * 14 October 2018
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse implements Serializable{
    private Boolean success;
}
