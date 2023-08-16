package com.spring.holaeat.service;

import com.spring.holaeat.domain.nutritions.Nutritions;
import com.spring.holaeat.domain.nutritions.NutritionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NutritionService {

    private NutritionsRepository nutritionsRepository;

    public List<Nutritions> findByFoodId(String foodId){

        List<Nutritions> list = nutritionsRepository.findByFoodId(foodId);

        return list;

    }
}
