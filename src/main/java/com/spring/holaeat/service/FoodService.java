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


        public void addFood(FoodRequestDto foodRequestDto){
//            String id = generateId();
            Food food = new Food(
//                    id,
                    foodRequestDto); // Food 생성자 수정 필요
            foodRepository.save(food);
        }

//        private String generateId() {
//            // 데이터베이스에서 가장 큰 ID 값 조회 후 +1
//            int maxId = foodRepository.findMaxId(); // foodRepository에서 해당 메소드를 구현해야 함
//            return "F" + String.format("%04d", maxId + 1); // "F0055", "F0056", ...
//        }


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
