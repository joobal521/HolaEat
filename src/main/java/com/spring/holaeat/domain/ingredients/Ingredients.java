package com.spring.holaeat.domain.ingredients;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;

@NoArgsConstructor
@Getter
@Entity
@Table(name="ingredients")
public class Ingredients {
    @Id
    private int ingrId;

    private String ingrName;
    private Boolean allergy;
    private Boolean month;
    private byte[] ingrImg;


    public Ingredients(int ingrId, String ingrName, Boolean allergy, Boolean month,byte[] ingrImg) {
        this.ingrId = ingrId;
        this.ingrName = ingrName;
        this.allergy = allergy;
        this.month = month;
        this.ingrImg = ingrImg;
    }

    public Ingredients(String ingrName, Boolean allergy, Boolean month,byte[] ingrImg) {
        this.ingrName = ingrName;
        this.allergy = allergy;
        this.month = month;
        this.ingrImg = ingrImg;

    }

    public Ingredients(IngredientsRequestDto ingredientsRequestDto) {
        this.ingrName = ingredientsRequestDto.getIngrName();
        this.allergy = ingredientsRequestDto.getAllergy();
        this.month = ingredientsRequestDto.getMonth();

        if (ingredientsRequestDto.getIngrImg() != null) {
            try {
                this.ingrImg = ingredientsRequestDto.getIngrImg().getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            this.ingrImg=null;
        }
    }


    public void update(IngredientsRequestDto ingredientsRequestDto) {
        this.allergy = ingredientsRequestDto.getAllergy();
        this.ingrName = ingredientsRequestDto.getIngrName();
        this.month = ingredientsRequestDto.getMonth();


    }

}
