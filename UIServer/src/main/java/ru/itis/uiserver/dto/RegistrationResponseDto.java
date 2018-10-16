package ru.itis.uiserver.dto;

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
public class RegistrationResponseDto implements Serializable{
    private Boolean success;
}
