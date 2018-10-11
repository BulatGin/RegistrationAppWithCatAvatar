package ru.itis.catservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bulat Giniyatullin
 * 11 October 2018
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatDto {
    private String url;
}
