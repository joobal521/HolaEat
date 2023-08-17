package com.spring.holaeat.domain.food;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class FoodRequestDto {

    private String foodId;
    private String foodNational;
    private String foodGroup;
    private String foodName;
    private int foodWeight;
    private boolean allergyInfo;
    private boolean vegan;
    private boolean weightControl;
    private boolean balanced;
    private boolean sideDish;
    private byte[] foodImg;
    private Double kcal;
    private int carb;
    private int protein;
    private int fat;
    private int sugars;
    private int natrium;



}
