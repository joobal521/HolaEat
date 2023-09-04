package com.spring.holaeat.domain.ingredients;

import lombok.Getter;


@Getter
public class IngredientsResponseDto {
    private int ingrId;
    private String ingrName;
    private Boolean allergy;
    private Boolean month;
    private byte[] ingrImg;


}
