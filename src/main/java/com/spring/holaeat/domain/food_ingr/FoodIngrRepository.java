package com.spring.holaeat.domain.food_ingr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodIngrRepository extends JpaRepository<FoodIngr,Long> {


    List<FoodIngr> findAllByIngrId(String ingrId);

}
