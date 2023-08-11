package com.spring.holaeat.domain.ingredients;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientsRequestDto {

    private String ingrName;
    private Boolean allergy;
    private Boolean month;

}
