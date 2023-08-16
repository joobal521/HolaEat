package com.spring.holaeat.domain.food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,String> {

    @Query(nativeQuery = true,value = "SELECT * FROM food WHERE food_id = ?1")
    public List<Food> findFoodByFoodId(String foodId);

    @Query(nativeQuery = true, value = "SELECT food_name FROM food")
    List<String> getAllFoodNames();

    @Query(nativeQuery = true, value = "SELECT food_national FROM food")
    List<String> getAllFoodNationals();

    @Query(nativeQuery = true, value = "SELECT food_name FROM food WHERE food_national = ?1")
    List<String> getFoodNamesByNational(String foodNational);

    @Query(nativeQuery = true, value = "SELECT ingr_name FROM ingredients")
    List<String> getAllIngrNames();


    byte[] getFoodImgByFoodId(String foodId);
}
