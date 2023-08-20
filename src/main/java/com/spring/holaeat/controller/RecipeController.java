package com.spring.holaeat.controller;

import com.spring.holaeat.domain.food.Food;
import com.spring.holaeat.domain.nutritions.Nutritions;
import com.spring.holaeat.domain.recipe.Recipe;
import com.spring.holaeat.service.FoodService;
import com.spring.holaeat.service.NutritionsService;
import com.spring.holaeat.service.RecipeService;
import com.spring.holaeat.util.ImageParsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private final FoodService foodService;

    private final NutritionsService nutritionsService;

    @Autowired
    public RecipeController (NutritionsService nutritionsService, RecipeService recipeService, FoodService foodService){
        this.nutritionsService = nutritionsService;
        this.recipeService = recipeService;
        this.foodService = foodService;
    }

    @GetMapping("/getRecipe/{foodId}")
    public String getRecipe(@PathVariable String foodId, Model model) {
        List<Recipe> recipe = recipeService.findStepsByFoodId(foodId);
        model.addAttribute("recipe", recipe);
        Map<Integer,String> imageMap = new HashMap<>();

        String foodImg = ImageParsor.parseBlobToBase64(foodService.getFoodImgByFoodId(foodId));
        model.addAttribute("foodImg",foodImg);

        Food food = foodService.findFoodByFoodId(foodId);
        model.addAttribute("food",food);
        System.out.println("foodID:"+foodId);
//        List<Nutritions> nutritions = nutritionsService.getNutritions(foodId);
//        model.addAttribute("nutrition",nutritions);

        return "getRecipe"; // 해당 레시피 정보를 보여줄 JSP 파일의 이름
    }




}
