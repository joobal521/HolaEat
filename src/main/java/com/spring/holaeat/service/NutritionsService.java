package com.spring.holaeat.service;

import com.spring.holaeat.domain.nutritions.Nutritions;
import com.spring.holaeat.domain.nutritions.NutritionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NutritionsService {

    private NutritionsRepository nutritionsRepository;

    public NutritionsService(NutritionsRepository nutritionsRepository) {
        this.nutritionsRepository = nutritionsRepository;
    }

    public List<Nutritions> findAllByFoodId(String foodId){
        System.out.println("service foodID: " +foodId);
        return nutritionsRepository.findAllByFoodId(foodId);
    }

//    public List<Nutritions> getNutritions(String foodId){
//          List<Nutritions> list = new ArrayList<>();
//           if (nutritionsRepository.findAll()==null){
//               System.out.println("findbyIdNull");
//               return  nutritionsRepository.findByFoodId(foodId);
//           }else{
//               System.out.println("findAllNull");
//               return nutritionsRepository.findAll();
//           }
//
//
//    }
}
