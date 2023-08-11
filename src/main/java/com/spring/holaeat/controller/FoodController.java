package com.spring.holaeat.controller;

import com.spring.holaeat.domain.food.FoodRepository;
import com.spring.holaeat.domain.ingredients.Ingredients;
import com.spring.holaeat.domain.ingredients.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FoodController {

    @Autowired
    private FoodRepository foodRepository; // FoodRepository는 실제로 데이터베이스에서 데이터를 가져오는 데 사용되는 클래스입니다.
    private IngredientsRepository ingredientsRepository;

    @GetMapping("/menu")
    public String getFoodNames(@RequestParam(name = "national", defaultValue = "all") String foodNational, Model model) {
        List<String> foodNames;

        if ("all".equals(foodNational)) {
            foodNames = foodRepository.getAllFoodNames();
        } else {
            foodNames = foodRepository.getFoodNamesByNational(foodNational);
        }

        List<String> foodNationals = foodRepository.getAllFoodNationals();
        List<String> ingrNames = foodRepository.getAllIngrNames(); // 재료 이름 가져오기

        model.addAttribute("foodNames", foodNames);
        model.addAttribute("foodNationals", foodNationals);
        model.addAttribute("ingrNames", ingrNames); // 재료 이름 추가

        return "menu";
    }

//    public String getAllIngrNames(Model model) {
//        List<String> ingrNames = ingredientsRepository.getAllIngrNames();
//        model.addAttribute("ingrNames", ingrNames);
//        return "menu"; // JSP 페이지 이름으로 변경해야 함
//    }



}