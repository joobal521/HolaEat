package com.spring.holaeat.domain.ingredients;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter

public class Ingredients {
    private int ingr_id;
    private String ingr_name;
    private Boolean allergy;
    private Boolean month;

    public Ingredients(int ingr_id, String ingr_name, Boolean allergy, Boolean month) {
        this.ingr_id = ingr_id;
        this.ingr_name = ingr_name;
        this.allergy = allergy;
        this.month = month;
    }
}
