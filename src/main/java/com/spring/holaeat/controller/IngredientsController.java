package com.spring.holaeat.controller;

import com.spring.holaeat.domain.food_ingr.FoodIngr;
import com.spring.holaeat.domain.ingredients.Ingredients;
import com.spring.holaeat.service.FoodIngrService;
import com.spring.holaeat.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IngredientsController {
    private final IngredientsService ingredientsService;
    private final FoodIngrService foodIngrService;


    @Autowired
    public IngredientsController(IngredientsService ingredientsService, FoodIngrService foodIngrService) {
        this.ingredientsService = ingredientsService;
        this.foodIngrService = foodIngrService;
    }

    @GetMapping("/ingredients")
    public String getIngredients(Model model) {
        List<Ingredients> ingredientsList = ingredientsService.findByMonthEquals();
        System.out.println("ingrservice");
        List<FoodIngr> allFoodIngrList = new ArrayList<>(); // 전체 foodIngrList를 담을 리스트
        System.out.println("foodlist");
        for (Ingredients ingredient : ingredientsList) {
            String ingrId = String.valueOf(ingredient.getIngrId());
            List<FoodIngr> foodIngrList = foodIngrService.findFoodIdByIngrId(ingrId);
            allFoodIngrList.addAll(foodIngrList); // 현재 ingrId에 해당하는 foodIngrList를 전체 리스트에 추가
        }
        System.out.println("foodservice for");
        model.addAttribute("ingredientsList", ingredientsList);
        model.addAttribute("allFoodIngrList", allFoodIngrList);

        return "ingredients"; // ingredients.jsp
    }


}


