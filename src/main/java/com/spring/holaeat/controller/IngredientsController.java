package com.spring.holaeat.controller;

import com.spring.holaeat.domain.food.Food;
import com.spring.holaeat.domain.food_ingr.FoodIngr;
import com.spring.holaeat.domain.ingredients.Ingredients;
import com.spring.holaeat.domain.ingredients.IngredientsRepository;
import com.spring.holaeat.service.FoodIngrService;
import com.spring.holaeat.service.FoodService;
import com.spring.holaeat.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IngredientsController {
    private final IngredientsService ingredientsService;
    private final FoodIngrService foodIngrService;
    private final FoodService foodService;


    @Autowired
    public IngredientsController(IngredientsService ingredientsService, FoodIngrService foodIngrService, FoodService foodService) {
        this.ingredientsService = ingredientsService;
        this.foodIngrService = foodIngrService;
        this.foodService = foodService;
    }

    @GetMapping("/ingredients")
    public String getIngredients(Model model) {
        List<Ingredients> ingredientsList = ingredientsService.findByMonthEquals();
        List<FoodIngr> monthFoodIngrList = new ArrayList<>(); // 전체 foodIngrList를 담을 리스트
        List<Food> monthFoods = new ArrayList<>();

        for (Ingredients ingredient : ingredientsList) {
            String ingrId = String.valueOf(ingredient.getIngrId());
            List<FoodIngr> foodIngrList = foodIngrService.findFoodIdByIngrId(ingrId);
            monthFoodIngrList.addAll(foodIngrList); // 현재 ingrId에 해당하는 foodIngrList를 전체 리스트에 추가
        }

        for (FoodIngr foodIngr : monthFoodIngrList) {
            String foodId = String.valueOf(foodIngr.getFoodId());
            List<Food> foods = foodService.findFoodByFoodId(foodId);
            monthFoods.addAll(foods);
        }

        model.addAttribute("ingredientsList", ingredientsList);//식재료리스트
        model.addAttribute("monthFoodIngrList", monthFoodIngrList);//
        model.addAttribute("monthFoods", monthFoods);

        return "ingredients"; // ingredients.jsp
    }


}


