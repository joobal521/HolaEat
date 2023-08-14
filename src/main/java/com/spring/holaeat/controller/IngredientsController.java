package com.spring.holaeat.controller;

import com.spring.holaeat.domain.food.Food;
import com.spring.holaeat.domain.food_ingr.FoodIngr;
import com.spring.holaeat.domain.ingredients.Ingredients;
import com.spring.holaeat.domain.ingredients.IngredientsRequestDto;
import com.spring.holaeat.service.FoodIngrService;
import com.spring.holaeat.service.FoodService;
import com.spring.holaeat.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IngredientsController {
    private final IngredientsService ingredientsService;
    private final FoodIngrService foodIngrService;
    private final FoodService foodService;
    private final  IngredientsRequestDto ingredientsRequestDto;


    @Autowired
    public IngredientsController(IngredientsService ingredientsService, FoodIngrService foodIngrService, FoodService foodService, IngredientsRequestDto ingredientsRequestDto) {
        this.ingredientsService = ingredientsService;
        this.foodIngrService = foodIngrService;
        this.foodService = foodService;
        this.ingredientsRequestDto = ingredientsRequestDto;
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


    @PostMapping("/savePreferredIngredients")
    @ResponseBody
    public String savePreferredIngredient(@RequestBody IngredientsRequestDto requestDto, HttpSession session) {
        String userId = (String) session.getAttribute("log");
        if (userId != null) {
            String ingrId = ingredientsService.findIngrIdByName(requestDto.getIngrName());
            ingredientsService.savePreferredIngredient(userId, ingrId);
            return "success";
        } else {
            return "userId not found"; // 세션에서 userId를 찾을 수 없는 경우 처리
        }
    }


//    private String getSessionUserId() {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (attributes != null) {
//            HttpSession session = attributes.getRequest().getSession(false);
//            if (session != null) {
//                return (String) session.getAttribute("userId");
//            }
//        }
//        return null;
//    }


}


