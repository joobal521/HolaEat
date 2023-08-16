package com.spring.holaeat.domain.food;

import lombok.Getter;
import lombok.Setter;

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


}
