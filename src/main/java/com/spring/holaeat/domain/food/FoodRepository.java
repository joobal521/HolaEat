package com.spring.holaeat.domain.food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,String> {

    @Query(nativeQuery = true,value = "SELECT * FROM food WHERE food_id = ?1")
    Food findFoodByFoodId(String foodId);

    @Query(nativeQuery = true,value = "SELECT * FROM food WHERE food_id = ?1")
    List<Food> findFoodListByFoodId(String foodId);


    @Query(nativeQuery = true, value = "SELECT food_name FROM food")
    List<String> getAllFoodNames();

    @Query(nativeQuery = true, value = "SELECT food_national FROM food")
    List<String> getAllFoodNationals();

    @Query(nativeQuery = true, value = "SELECT food_name FROM food WHERE food_national = ?1")
    List<String> getFoodNamesByNational(String foodNational);

    @Query(nativeQuery = true, value = "SELECT ingr_name FROM ingredients")
    List<String> getAllIngrNames();


    @Query(nativeQuery = true, value = "SELECT food_img from food where food_id = ?1")
    byte[] getFoodImgByFoodId(String foodId);

    List<Food> findAll();

    @Query(nativeQuery = true,value = "SELECT food_id from food")
    List<String> getAllFoodIds();

    Food findByFoodName(String foodName);


//      List<Food> findFoodIdAndAllergyInfoAndBalancedAndFoodGroupAndFoodNameAndFoodNationalAndFoodWeightAndSideDishAndVeganAndWeightControlAndKcalAndCarbAndProteinAndFatAndSugarsAndNatrium();


}
