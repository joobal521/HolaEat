package com.spring.holaeat.controller;

import com.spring.holaeat.domain.ingredients.Ingredients;
import com.spring.holaeat.domain.recipe.Recipe;
import com.spring.holaeat.service.IngredientsService;
import com.spring.holaeat.service.RecipeService;
import com.spring.holaeat.util.ImageParsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RecipeController {

    private final IngredientsService ingredientsService;
    private final RecipeService recipeService;

    @Autowired
    public RecipeController (IngredientsService ingredientsService, RecipeService recipeService){
        this.ingredientsService = ingredientsService;
        this.recipeService = recipeService;
    }

    @GetMapping("/getRecipe/{foodId}?{ingrId}")
    public String getRecipe(@PathVariable String foodId,@PathVariable String ingrId, Model model) {
        List<Recipe> recipe = recipeService.findStepsByFoodId(foodId);

        Ingredients ingredients = ingredientsService.findById(ingrId);
        String blob = null;
        if(ingredients.getIngrImg()!=null) {
            blob = ImageParsor.parseBlobToBase64(ingredients.getIngrImg());
            model.addAttribute("blob",blob);
        }
        model.addAttribute("recipe", recipe);

        return "getRecipe"; // 해당 레시피 정보를 보여줄 JSP 파일의 이름
    }



}
