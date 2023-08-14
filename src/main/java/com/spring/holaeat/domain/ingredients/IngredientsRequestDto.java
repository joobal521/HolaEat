package com.spring.holaeat.domain.ingredients;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class IngredientsRequestDto {
    private String ingrId;
    private String ingrName;
    private Boolean allergy;
    private Boolean month;
    private byte[] ingrImg;



}
