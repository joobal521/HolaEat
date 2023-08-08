package com.spring.holaeat.service;

import com.spring.holaeat.domain.ingredients.Ingredients;
import com.spring.holaeat.domain.ingredients.IngredientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IngredientsService {
    private final IngredientsRepository ingredientsRepository;


    public List<Ingredients> findByMonthEquals() {
        List<Ingredients> list = ingredientsRepository.findByMonthEquals();
        return list;
    }


}
