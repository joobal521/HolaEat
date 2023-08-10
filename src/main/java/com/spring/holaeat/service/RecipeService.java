package com.spring.holaeat.service;

import com.spring.holaeat.domain.recipe.Recipe;
import com.spring.holaeat.domain.recipe.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public List<Recipe> findStepsByFoodId(String foodId) {
        System.out.println("service:"+foodId);
        List<Recipe> recipe = recipeRepository.findStepsByFoodId(foodId);
        if(!recipe.isEmpty()){
            for(Recipe test:recipe){
                System.out.println(test.getStep_01());
                System.out.println(test.getStep_02());
                System.out.println(test.getStep_03());
                System.out.println(test.getStep_04());
                System.out.println(test.getStep_05());
            }
        }else{
            System.out.println("비어있음");
        }
        return recipe;
    }
}
