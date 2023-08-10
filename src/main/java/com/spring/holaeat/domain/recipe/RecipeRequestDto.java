package com.spring.holaeat.domain.recipe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeRequestDto {
    private String foodId;
    private String step_01;
    private String step_02;
    private String step_03;
    private String step_04;
    private String step_05;
}
