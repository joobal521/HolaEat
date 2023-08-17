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

    @Id
    private long nutrId;
    private String foodId;
    private int kcal;
    private int carb;
    private int protein;
    private int fat;
    private int sugars;
    private int natrium;


}
