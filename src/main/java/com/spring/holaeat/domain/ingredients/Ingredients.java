package com.spring.holaeat.domain.ingredients;

import com.spring.holaeat.domain.review.ReviewRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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


    public Ingredients(int ingrId, String ingrName, Boolean allergy, Boolean month) {
        this.ingrId = ingrId;
        this.ingrName = ingrName;
        this.allergy = allergy;
        this.month = month;
    }


    public void update(IngredientsRequestDto ingredientsRequestDto) {
        this.allergy = ingredientsRequestDto.getAllergy();
        this.ingrName = ingredientsRequestDto.getIngrName();
        this.month = ingredientsRequestDto.getMonth();


    }

}
