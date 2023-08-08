package com.spring.holaeat.domain.recipe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeRequestDto {
    private String foodId;
    private String step01;
    private String step02;
    private String step03;
    private String step04;
    private String step05;
}
