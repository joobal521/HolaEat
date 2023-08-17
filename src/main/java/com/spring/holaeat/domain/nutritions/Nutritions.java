package com.spring.holaeat.domain.nutritions;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Table(name = "nutritions")
@Entity

public class Nutritions {


    @NaturalId
    private String foodId; // This field is the primary key

    private int kcal;
    private int carb;
    private int protein;
    private int fat;
    private int sugars;
    private int natrium;
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
