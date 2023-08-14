package com.spring.holaeat.domain.ingredients;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name="ingredients")
public class Ingredients {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String ingrId;

    private String ingrName;
    private Boolean allergy;
    private Boolean month;


    public Ingredients(String ingrId, String ingrName, Boolean allergy, Boolean month) {
        this.ingrId = ingrId;
        this.ingrName = ingrName;
        this.allergy = allergy;
        this.month = month;
    }

    public Ingredients(String ingrName, Boolean allergy, Boolean month) {
        this.ingrName = ingrName;
        this.allergy = allergy;
        this.month = month;
    }

    public Ingredients(IngredientsRequestDto ingredientsRequestDto) {
        this.ingrName = ingredientsRequestDto.getIngrName();
        this.allergy = ingredientsRequestDto.getAllergy();
        this.month = ingredientsRequestDto.getMonth();
    }


    public void update(IngredientsRequestDto ingredientsRequestDto) {
        this.allergy = ingredientsRequestDto.getAllergy();
        this.ingrName = ingredientsRequestDto.getIngrName();
        this.month = ingredientsRequestDto.getMonth();


    }

}
