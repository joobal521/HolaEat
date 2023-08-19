package com.spring.holaeat.service;

import com.spring.holaeat.domain.food_ingr.FoodIngr;
import com.spring.holaeat.domain.food_ingr.FoodIngrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodIngrService {
    private final FoodIngrRepository foodIngrRepository;
    public List<FoodIngr> findFoodIdByIngrId(int ingrId) {
        String id = String.valueOf(ingrId);
        List<FoodIngr> list = foodIngrRepository.findAllByIngrId(id);
//        for(FoodIngr fud : list){
//            System.out.println(fud.getFoodIngrId());
//            System.out.println(fud.getIngrId());
//            System.out.println(fud.getFoodId());
//            System.out.println("------");
//        }
        return list;

    }


}
