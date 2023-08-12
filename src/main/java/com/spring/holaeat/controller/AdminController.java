package com.spring.holaeat.controller;

import com.spring.holaeat.domain.admin.Admin;
import com.spring.holaeat.domain.admin.AdminRepository;
import com.spring.holaeat.domain.ingredients.Ingredients;
import com.spring.holaeat.domain.ingredients.IngredientsRepository;
import com.spring.holaeat.domain.ingredients.IngredientsRequestDto;
import com.spring.holaeat.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@SessionAttributes("authority")
@Controller
public class AdminController {
    private final AdminRepository adminRepository;
    private final IngredientsService ingredientsService;


    @Autowired
    public AdminController(AdminRepository adminRepository, IngredientsService ingredientsService) {
        this.adminRepository = adminRepository;
        this.ingredientsService = ingredientsService;
    }

@PostMapping("gainpower")
public String gainPower(@RequestParam("adminid") String id, @RequestParam("adminpwd") String pwd, HttpSession session) {
    List<Admin> admin = adminRepository.findAdminByIdAndPassword(id, pwd);

    if (admin.isEmpty()) {
        System.out.println("관리자 로그인 실패");
        return "gainpower"; // 실패 시 해당 뷰로 반환
    } else {
        session.setAttribute("authority", "POWERED");
        System.out.println("관리자 로그인");
        return "redirect:/admin"; // 리다이렉트하여 주소창의 URL도 변경
    }
}

    @GetMapping("adminIngr")
    public String getIngredients(Model model){
        List<Ingredients> list = ingredientsService.getAllIngredients();
        model.addAttribute("ingredientList",list);

        return "adminIngr";
    }

    @PutMapping("adminIngr/{ingrId}")
    public String updateIngredient(@PathVariable int ingrId, @ModelAttribute IngredientsRequestDto ingredientsRequestDto){

        Ingredients ingredient = ingredientsService.findById(ingrId);

        ingredientsService.update(ingredient,ingredientsRequestDto);

        return "admin/adminIngr";
    }



}
