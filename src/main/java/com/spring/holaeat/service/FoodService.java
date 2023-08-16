package com.spring.holaeat.service;

import com.spring.holaeat.domain.food.Food;
import com.spring.holaeat.domain.food.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public List<Food> findFoodByFoodId(String foodId){
        List<Food> list = foodRepository.findFoodByFoodId(foodId);
        return list;
    }
    public byte[] getFoodImgByFoodId(String foodId){
        return foodRepository.getFoodImgByFoodId(foodId);
    }
}
