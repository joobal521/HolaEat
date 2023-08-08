package com.spring.holaeat.domain.ingredients;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientsRequestDto {

    private int ingr_id;
    private String ingr_name;
    private Boolean allergy;
    private Boolean month;

}
