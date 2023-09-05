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

    public void updateFoodWithImage(Food food, FoodRequestDto foodRequestDto, byte[] imgBytes){
        food.update(foodRequestDto);
        food.remainImg(imgBytes);

        foodRepository.save(food);
    }

    public void remainImg(Food food, byte[] img){
        food.remainImg(img);
        foodRepository.save(food);
    }

    public List<Food> getAllWithoutImg(){
        return foodRepository.findAllWithoutImg();
    }


    public String generateNewFoodId() {
        // 현재 데이터베이스에 저장되어 있는 모든 foodId를 가져옵니다.
        List<String> existingFoodIds = foodRepository.getAllFoodIds();

        // 새로운 foodId를 생성할 숫자를 찾습니다.
        int nextNumber = 1;
        for (String existingFoodId : existingFoodIds) {
            String numberPart = existingFoodId.substring(1); // F 다음의 숫자 부분
            int existingNumber = Integer.parseInt(numberPart);
            if (existingNumber >= nextNumber) {
                nextNumber = existingNumber + 1;
            }
        }

        // 생성된 숫자를 4자리로 만들어줍니다.
        String formattedNumber = String.format("%04d", nextNumber);

        // 최종적으로 생성된 foodId를 반환합니다.
        return "F" + formattedNumber;
    }

    public void addFood(FoodRequestDto foodRequestDto) {
        String newFoodId = generateNewFoodId();
        System.out.println(newFoodId);
        foodRequestDto.setFoodId(newFoodId);


        Food food = new Food(foodRequestDto);
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

    public void deleteFoodByFoodId(String foodId){
        foodRepository.deleteById(foodId);
    }
}
