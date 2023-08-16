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
import java.util.Random;

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

    public String findIngrIdByName(String ingrName) {
        Ingredients ingredient = ingredientsRepository.findByIngrName(ingrName);
        return ingredient != null ? String.valueOf(ingredient.getIngrId()) : "0";
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

    public void addIngredient(IngredientsRequestDto ingredientsRequestDto){
        Ingredients ingredients = new Ingredients(ingredientsRequestDto);
        System.out.println("service:"+ingredientsRequestDto.getIngrId());
        ingredientsRepository.save(ingredients);
    }
    @Transactional
    public void deleteIngredientsByIngrId(int id){
        ingredientsRepository.deleteIngredientsByIngrId(id);
    }


    public List<Ingredients> getAllIngredients(){
        List<Ingredients> list = ingredientsRepository.findAll();
        return list;
    }
    public void savePreferredIngredient(String userId, int ingrId) {
        Prefer prefer = new Prefer();
        prefer.setUserId(userId);
        prefer.setIngrId(String.valueOf(ingrId));
        preferRepository.save(prefer);
    }

    public void saveDislikedIngredient(String userId, int ingrId) {
        Dislike dislike = new Dislike();
        dislike.setUserId(userId);
        dislike.setIngrId(String.valueOf(ingrId)); // int 타입으로 설정
        dislikeRepository.save(dislike);
    }

//    public String generateIngrId() {
//        String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        int ID_LENGTH = 10;
//
//        StringBuilder idBuilder = new StringBuilder();
//        Random random = new Random();
//
//        for (int i = 0; i < ID_LENGTH; i++) {
//            int randomIndex = random.nextInt(CHARACTERS.length());
//            char randomChar = CHARACTERS.charAt(randomIndex);
//            idBuilder.append(randomChar);
//        }
//
//        return idBuilder.toString();
//    }



}


