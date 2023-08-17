package com.spring.holaeat.service;

import com.spring.holaeat.domain.food.Food;
import com.spring.holaeat.domain.food.FoodRepository;
import com.spring.holaeat.domain.food.FoodRequestDto;
import com.spring.holaeat.domain.ingredients.Ingredients;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public Food findFoodByFoodId(String foodId){
          return foodRepository.findFoodByFoodId(foodId);
    }

    public void update(Food food, FoodRequestDto foodRequestDto){
        food.update(foodRequestDto);
        foodRepository.save(food);
    }

    public void remainImg(Food food, byte[] img){
        food.remainImg(img);
        foodRepository.save(food);
    }
    public List<Food> findFoodListByFoodId(String foodId){
        return foodRepository.findFoodListByFoodId(foodId);
    }
    public byte[] getFoodImgByFoodId(String foodId){
        return foodRepository.getFoodImgByFoodId(foodId);
    }

    public List<Food> getAllFood(){
        return foodRepository.findAll();
    }
}
