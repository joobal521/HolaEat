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


    public List<FoodIngr> findFoodIdByIngrId(String ingrId) {
        List<FoodIngr> list = foodIngrRepository.findFoodIdByIngrId(ingrId);
        return list;

    }
}
