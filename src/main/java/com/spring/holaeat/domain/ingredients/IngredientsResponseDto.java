package com.spring.holaeat.domain.ingredients;

import lombok.Getter;

@Getter
public class IngredientsResponseDto {
    private int ingr_id;
    private String ingr_name;
    private Boolean allergy;
    private Boolean month;


}
