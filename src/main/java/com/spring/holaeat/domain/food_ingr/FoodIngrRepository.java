package com.spring.holaeat.domain.food_ingr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodIngrRepository extends JpaRepository<FoodIngr,String> {


    @Query(nativeQuery = true,value = "SELECT * FROM food_ingr WHERE ingr_id = ?1")
    public List<FoodIngr> findFoodIdByIngrId(String ingrId);

}
