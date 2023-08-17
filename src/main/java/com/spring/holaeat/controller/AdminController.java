package com.spring.holaeat.controller;

import com.spring.holaeat.domain.admin.Admin;
import com.spring.holaeat.domain.admin.AdminRepository;
import com.spring.holaeat.domain.ingredients.Ingredients;
import com.spring.holaeat.domain.ingredients.IngredientsRequestDto;
import com.spring.holaeat.domain.menu.Menu;
import com.spring.holaeat.service.IngredientsService;
import com.spring.holaeat.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@SessionAttributes("authority")
@Controller
public class AdminController {
    private final AdminRepository adminRepository;
    private final IngredientsService ingredientsService;
    private final MenuService menuService;


    @Autowired
    public AdminController(AdminRepository adminRepository, IngredientsService ingredientsService, IngredientsRequestDto ingredientsRequestDto, MenuService menuService) {
        this.adminRepository = adminRepository;
        this.ingredientsService = ingredientsService;
        this.menuService = menuService;
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
        ingredientsService.addIngredient(ingredientsRequestDto);


        return "adminIngr";
    }

    //재료정보수정
//    @PutMapping(value = "adminIngr/{ingrId}", consumes = "multipart/form-data")
//    public String updateIngredient(@PathVariable int ingrId, @RequestBody IngredientsRequestDto ingredientsRequestDto) {
//        Ingredients ingredient = ingredientsService.findById(ingrId);
//        ingredientsService.update(ingredient, ingredientsRequestDto);
//
//        return "adminIngr";
//    }

    @Transactional
    @PutMapping(value = "adminIngr/{ingrId}", consumes = "multipart/form-data")
    public String updateIngredient(@PathVariable int ingrId, @ModelAttribute IngredientsRequestDto ingredientsRequestDto) {
        Ingredients ingredient = ingredientsService.findById(ingrId);

        ingredientsService.update(ingredient, ingredientsRequestDto);

        if(ingredientsRequestDto.getIngrImg()==null){
            byte[] img = ingredient.getIngrImg();
            ingredientsService.remainImg(ingredient,img);
        }

        return "adminIngr";
    }

    //재료삭제
    @DeleteMapping("adminIngr/delete/{ingrId}")
    public String deleteIngrByID(@PathVariable int ingrId){
        ingredientsService.deleteIngredientsByIngrId(ingrId);

        return "adminIngr";
    }

////메뉴관리
//    @GetMapping("adminMenu")
//    public String getAllMenu(Model model){
//
//
//
//        return "adminMenu";
//    }
//
//    //후기게시판관리
//    @GetMapping("adminReview")
//    public String getReview(Model model){
//
//        return "adminReview";
//    }
//
//    //유저관리
//    @GetMapping("adminUser")
//    public String getUser(Model model){
//
//        return "adminUser";
//    }
//
//    //건강정보관리
//    @GetMapping("adminHealth")
//    public String gethealth(Model mode){
//
//        return "adminHealth";
//    }


}
