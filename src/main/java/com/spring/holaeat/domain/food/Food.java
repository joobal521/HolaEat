package com.spring.holaeat.domain.food;

import lombok.Getter;

@Getter
public class Food {

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

    public Food(String food_id, String food_national, String food_group, String food_name, int food_weight, boolean allergy_info, boolean vegan, boolean weight_control, boolean balanced, boolean side_dish, byte[] file) {
        this.food_id = food_id;
        this.food_national = food_national;
        this.food_group = food_group;
        this.food_name = food_name;
        this.food_weight = food_weight;
        this.allergy_info = allergy_info;
        this.vegan = vegan;
        this.weight_control = weight_control;
        this.balanced = balanced;
        this.side_dish = side_dish;
        this.file = file;
    }
}
