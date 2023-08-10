package com.spring.holaeat.controller;

import com.spring.holaeat.domain.recipe.Recipe;
import com.spring.holaeat.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController (RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping("/getRecipe/{foodId}")
    public String getRecipe(@PathVariable String foodId, Model model) {
        List<Recipe> recipe = recipeService.findStepsByFoodId(foodId);
        model.addAttribute("recipe", recipe);
        return "getRecipe"; // 해당 레시피 정보를 보여줄 JSP 파일의 이름
    }



}
