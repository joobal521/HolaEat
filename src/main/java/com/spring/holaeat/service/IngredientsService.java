package com.spring.holaeat.service;

import com.spring.holaeat.domain.dislike.Dislike;
import com.spring.holaeat.domain.dislike.DislikeRepository;
import com.spring.holaeat.domain.ingredients.Ingredients;
import com.spring.holaeat.domain.ingredients.IngredientsRepository;
import com.spring.holaeat.domain.ingredients.IngredientsRequestDto;
import com.spring.holaeat.domain.prefer.Prefer;
import com.spring.holaeat.domain.prefer.PreferRepository;
import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IngredientsService {
    private final IngredientsRepository ingredientsRepository;
    private final PreferRepository preferRepository;
    private final DislikeRepository dislikeRepository;


    public List<Ingredients> findByMonthEquals() {
        List<Ingredients> list = ingredientsRepository.findByMonthEquals();
        return list;
    }

    public int findIngrIdByName(String ingrName) {
        Ingredients ingredient = ingredientsRepository.findByIngrName(ingrName);
        return ingredient != null ? ingredient.getIngrId() : 0;
    }

    public Ingredients findById(int id) {
        Ingredients ingredient = ingredientsRepository.findByIngrId(id);
        return ingredient;
    }

    @Transactional
    public void update(Ingredients ingredient, IngredientsRequestDto ingredientsRequestDto){

        ingredient.update(ingredientsRequestDto);
        ingredientsRepository.save(ingredient);
    }


    public List<Ingredients> getAllIngredients(){
        List<Ingredients> list = ingredientsRepository.findAll();
        return list;
    }
    public void savePreferredIngredient(String userId, int ingrId) {
        Prefer prefer = new Prefer();
        prefer.setUserId(userId);
        prefer.setIngrId(ingrId);
        preferRepository.save(prefer);
    }

    public void saveDislikedIngredient(String userId, int ingrId) {
        Dislike dislike = new Dislike();
        dislike.setUserId(userId);
        dislike.setIngrId(ingrId); // int 타입으로 설정
        dislikeRepository.save(dislike);
    }



}


