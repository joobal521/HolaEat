package com.spring.holaeat.controller;

import com.spring.holaeat.domain.admin.Admin;
import com.spring.holaeat.domain.admin.AdminRepository;
import com.spring.holaeat.domain.ingredients.Ingredients;
import com.spring.holaeat.domain.ingredients.IngredientsRequestDto;
import com.spring.holaeat.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@SessionAttributes("authority")
@Controller
public class AdminController {
    private final AdminRepository adminRepository;
    private final IngredientsService ingredientsService;


    @Autowired
    public AdminController(AdminRepository adminRepository, IngredientsService ingredientsService, IngredientsRequestDto ingredientsRequestDto) {
        this.adminRepository = adminRepository;
        this.ingredientsService = ingredientsService;
    }

    //관리자 로그인
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

//재료관리
    @GetMapping("adminIngr")
    public String getIngredients(Model model){
        List<Ingredients> list = ingredientsService.getAllIngredients();
        model.addAttribute("ingredientList",list);

        return "adminIngr";
    }

    //재료정보생성
    @PostMapping(value="adminIngr/create",consumes = "multipart/form-data")
    public String addIngredient(@ModelAttribute IngredientsRequestDto ingredientsRequestDto) {
        System.out.println("creating");


        System.out.println(ingredientsRequestDto.getIngrName());
        System.out.println(ingredientsRequestDto.getIngrId());
        System.out.println(ingredientsRequestDto.getAllergy());
        System.out.println(ingredientsRequestDto.getMonth());
        System.out.println(ingredientsRequestDto.getIngrId());
        System.out.println(ingredientsRequestDto.getIngrImg());
        ingredientsService.addIngredient(ingredientsRequestDto);
        System.out.println("created");

        return "adminIngr";
    }

    //재료정보수정
    @PutMapping(value = "adminIngr/{ingrId}",consumes = "multipart/form-data")
    public String updateIngredient(@PathVariable int ingrId, @RequestBody IngredientsRequestDto ingredientsRequestDto) {
        Ingredients ingredient = ingredientsService.findById(ingrId);
        ingredientsService.update(ingredient, ingredientsRequestDto);

        return "adminIngr";
    }

    //재료삭제
    @DeleteMapping("adminIngr/delete/{ingrId}")
    public String deleteIngrByID(@PathVariable int ingrId){
        ingredientsService.deleteIngredientsByIngrId(ingrId);

        return "adminIngr";
    }




}
