package com.spring.holaeat.domain.nutritions;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Entity
@Table(name="nutritions")
public class Nutritions {

    @Id
    private String foodId;

    private int kcal;
    private int carb;
    private int protein;
    private int fat;
    private int sugars;
    private int natrium;
    @javax.persistence.Id
    private Long id;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
