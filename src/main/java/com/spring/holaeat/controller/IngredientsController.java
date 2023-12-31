package com.spring.holaeat.controller;

import com.spring.holaeat.domain.food.Food;
import com.spring.holaeat.domain.food_ingr.FoodIngr;
import com.spring.holaeat.domain.ingredients.Ingredients;
import com.spring.holaeat.domain.ingredients.IngredientsRequestDto;
import com.spring.holaeat.service.FoodIngrService;
import com.spring.holaeat.service.FoodService;
import com.spring.holaeat.service.IngredientsService;
import com.spring.holaeat.util.ImageParsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String ingredientsOfMonth(Model model){
        //이달의 재료뽑기
        List<Ingredients> monthIngr = ingredientsService.findByMonthEquals();

        List<FoodIngr> foodIngr = new ArrayList<>();
        List<Food> monthFood = new ArrayList<>();
        Map<Integer,String> imageMap = new HashMap<>();

        //이달의 재료로 만들 수 있는 음식목록 뽑기
        for(Ingredients ingredients : monthIngr){
            foodIngr.addAll(foodIngrService.findFoodIdByIngrId(ingredients.getIngrId()));

            //재료 사진 가져오기
               String base64Image = ImageParsor.parseBlobToBase64(ingredients.getIngrImg());
               imageMap.put(ingredients.getIngrId(),base64Image);
        }

        for(FoodIngr showfoodIngr : foodIngr){
            monthFood.addAll(foodService.findFoodListByFoodId(showfoodIngr.getFoodId()));
        }

        model.addAttribute("ingrOfMonth",monthIngr);
        model.addAttribute("foodIngrList",foodIngr);
        model.addAttribute("monthFoodList",monthFood);
        model.addAttribute("blob",imageMap);

    return "ingredients";

    }

    @PostMapping("/savePreferredIngredients")
    @ResponseBody
    public String savePreferredIngredient(@RequestBody IngredientsRequestDto requestDto, HttpSession session) {
        String userId = (String) session.getAttribute("log");
        if (userId != null) {
            int ingrId = Integer.parseInt(ingredientsService.findIngrIdByName(requestDto.getIngrName()));
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


