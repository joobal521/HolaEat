package com.spring.holaeat.domain.nutritions;

import lombok.Getter;

@Getter
public class NutritionsResponseDto {
    private long nutrId;
    private String foodId;
    private int kcal;
    private int carb;
    private int protein;
    private int fat;
    private int sugars;
    private int natrium;
}
