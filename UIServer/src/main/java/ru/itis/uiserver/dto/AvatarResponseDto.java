package ru.itis.uiserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Bulat Giniyatullin
 * 15 October 2018
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AvatarResponseDto implements Serializable{
    private String userId;
    private String catUrl;
}
