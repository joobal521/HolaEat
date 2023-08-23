package com.spring.holaeat.controller;

import com.spring.holaeat.domain.food.Food;
import com.spring.holaeat.domain.food.FoodRepository;
import com.spring.holaeat.domain.ingredients.Ingredients;
import com.spring.holaeat.domain.ingredients.IngredientsRepository;
import com.spring.holaeat.service.FoodService;
import com.spring.holaeat.util.ImageParsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class FoodController {

    @Autowired
    private FoodRepository foodRepository; // FoodRepository는 실제로 데이터베이스에서 데이터를 가져오는 데 사용되는 클래스입니다.
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private FoodService foodService; // FoodService를 주입받음


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

    @GetMapping("/food/image/{foodName}")
    public ResponseEntity<byte[]> getFoodImage(@PathVariable String foodName) {
        Food food = foodRepository.findByFoodName(foodName);

        if (food != null && food.getFoodImg() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(food.getFoodImg(), headers, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }


//    @Autowired
//    @GetMapping("/food/{foodId}")
//    public String showFoodImage(@PathVariable String foodId, Model model) {
//        byte[] foodImg = foodRepository.getFoodImgByFoodId(foodId);
//        model.addAttribute("foodImg", foodImg);
//        return "foodImage"; // Thymeleaf template name
//    }
//



//    public String getAllIngrNames(Model model) {
//        List<String> ingrNames = ingredientsRepository.getAllIngrNames();
//        model.addAttribute("ingrNames", ingrNames);
//        return "menu"; // JSP 페이지 이름으로 변경해야 함
//    }



}