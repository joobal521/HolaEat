package com.spring.holaeat.domain.recipe;

import com.spring.holaeat.domain.ingredients.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,String> {

    @Query(nativeQuery = true,value = "SELECT * FROM recipe WHERE food_id = ?1")
    public List<Recipe> findStepsByFoodId(String foodId);

    List<Recipe> findAll();


}
