package com.spring.holaeat.controller;

import com.spring.holaeat.domain.ingredients.Ingredients;
import com.spring.holaeat.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IngredientsController {
    private final IngredientsService ingredientsService;

    @Autowired
    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping("/ingredients")
    public String getIngredients(Model model) {
        List<Ingredients> ingredientsList = ingredientsService.findByMonthEquals();
        model.addAttribute("ingredientsList", ingredientsList);


        return "ingredients"; // ingredients.jsp
    }
}
