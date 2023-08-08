package com.spring.holaeat.domain.nutritions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NutritionsRequestDto {
    private String foodId;
    private int kcal;
    private int carb;
    private int protein;
    private int fat;
    private int sugars;
    private int natrium;
}
