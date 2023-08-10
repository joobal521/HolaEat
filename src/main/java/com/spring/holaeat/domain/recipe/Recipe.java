package com.spring.holaeat.domain.recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Entity
@Table(name="recipe")
public class Recipe {
    @Id
    private String foodId;

    private String step_01;
    private String step_02;
    private String step_03;
    private String step_04;
    private String step_05;



}
