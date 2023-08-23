package com.spring.holaeat.controller;

import com.spring.holaeat.domain.admin.Admin;
import com.spring.holaeat.domain.admin.AdminRepository;
import com.spring.holaeat.domain.food.Food;
import com.spring.holaeat.domain.food.FoodRequestDto;
import com.spring.holaeat.domain.health.Health;
import com.spring.holaeat.domain.ingredients.Ingredients;
import com.spring.holaeat.domain.ingredients.IngredientsRequestDto;
import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SessionAttributes("authority")
@Controller
public class AdminController {
    private final AdminRepository adminRepository;
    private final IngredientsService ingredientsService;
    private final ReviewService reviewService;

    private final ReviewCommentService reviewCommentService;
    private final FoodService foodService;

    private final HealthService healthService;


    @Autowired
    public AdminController(AdminRepository adminRepository, IngredientsService ingredientsService, IngredientsRequestDto ingredientsRequestDto, MenuService menuService, ReviewService reviewService, ReviewCommentController reviewCommentController, ReviewCommentService reviewCommentService, FoodService foodService, HealthService healthService) {
        this.adminRepository = adminRepository;
        this.ingredientsService = ingredientsService;
        this.reviewService = reviewService;
        this.reviewCommentService = reviewCommentService;
        this.foodService = foodService;
        this.healthService=healthService;
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
    @Transactional
    @PutMapping(value = "adminIngr/{ingrId}", consumes = "multipart/form-data")
    public String updateIngredient(@PathVariable int ingrId, @ModelAttribute IngredientsRequestDto ingredientsRequestDto) {
        Ingredients ingredient = ingredientsService.findById(ingrId);

        ingredientsService.update(ingredient, ingredientsRequestDto);

        if(ingredientsRequestDto.getIngrImg()==null){
            byte[] img = ingredient.getIngrImg();
            ingredientsService.remainImg(ingredient,img);
        }

        return "admin";
    }

    //재료삭제
    @DeleteMapping("adminIngr/delete/{ingrId}")
    public String deleteIngrByID(@PathVariable int ingrId){
        ingredientsService.deleteIngredientsByIngrId(ingrId);

        return "admin";
    }

//메뉴관리
    @GetMapping("adminMenu")
    public String getAllMenu(Model model){
            List<Food> list = foodService.getAllFood();
//            List<Food> list = foodService.getFoodWithoutFoodImg();
            model.addAttribute("foodList",list);
        return "adminMenu";
    }


    //음식 추가
    @PostMapping(value="adminMenu/create",consumes = "multipart/form-data")
    public String addFood(@ModelAttribute FoodRequestDto foodRequestDto) {
        foodService.addFood(foodRequestDto);
        return "admin";
    }


    //음식수정
//    @Transactional
//    @PutMapping("adminMenu/{foodId}")
//    public String updateFood(@PathVariable String foodId, @ModelAttribute FoodRequestDto foodRequestDto){
//
//        Food food = foodService.findFoodByFoodId(foodId);
//        System.out.println(foodRequestDto.getFoodName());
//        System.out.println(Arrays.toString(foodRequestDto.getFoodImg()));
//
//        foodService.update(food,foodRequestDto);
//
//        if(foodRequestDto.getFoodImg()==null){
//            byte[] img = food.getFoodImg();
//            foodService.remainImg(food,img);
//        }
//        return "admin";
//    }
    @Transactional
    @PutMapping("adminMenu/{foodId}")
    public String updateFood(
            @PathVariable String foodId,
            @ModelAttribute FoodRequestDto foodRequestDto
    ) {
        Food food = foodService.findFoodByFoodId(foodId);

        if (foodRequestDto.getFoodImg() != null && !foodRequestDto.getFoodImg().isEmpty()) {
            try {
                byte[] imgBytes = foodRequestDto.getFoodImg().getBytes();
                foodService.updateFoodWithImage(food, foodRequestDto, imgBytes);
            } catch (IOException e) {
                // Handle the exception appropriately
            }
        } else {
            foodService.update(food, foodRequestDto);
        }

        return "admin";
    }

    @DeleteMapping("adminMenu/delete/{foodId}")
    public String deleteFood(@PathVariable String foodId){
        foodService.deleteFoodByFoodId(foodId);

        return "admin";
    }

    //후기게시판관리
//    @GetMapping("adminReview")
//    public String getReview(Model model){
//        List<Review> reviewList = reviewService.getAllReview();
//        System.out.println(reviewList.get(0).getTitle());
//        model.addAttribute("reviewList",reviewList);
//        return "adminReview";
//    }
    @GetMapping("adminReview")
    public String getReview(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        int reviewsPerPage = 10;
        List<Review> reviewList = reviewService.getReviewsByPage(page, reviewsPerPage);
        model.addAttribute("reviewList", reviewList);
        return "adminReview";
    }





    @DeleteMapping("adminReview/delete/{reviewNo}")
    public String deleteReview(@PathVariable long reviewNo){
        reviewCommentService.deleteByReviewNo(reviewNo);
        reviewService.delete(reviewNo);

        return "admin";
    }

//
//    //유저관리
//    @GetMapping("adminUser")
//    public String getUser(Model model){
//
//        return "adminUser";
//    }
//
    //건강정보관리
    @GetMapping("adminHealth")
    public String gethealth(Model model){
        List<Health> healthList=healthService.getAllHealth();
        model.addAttribute("healthList",healthList);
        return "adminHealth";
    }


}
