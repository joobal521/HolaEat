package com.spring.holaeat.domain.food;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodRequestDto {

    private String food_id;
    private String food_national;
    private String food_group;
    private String food_name;
    private int food_weight;
    private boolean allergy_info;
    private boolean vegan;
    private boolean weight_control;
    private boolean balanced;
    private boolean side_dish;
    private byte[] file;


}
