package ru.itis.catservice.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author Bulat Giniyatullin
 * 11 October 2018
 */

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AvatarResponseDto implements Serializable {
    private String userId;
    private String CatUrl;
}
