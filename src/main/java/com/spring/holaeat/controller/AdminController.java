package com.spring.holaeat.controller;

import com.spring.holaeat.domain.admin.Admin;
import com.spring.holaeat.domain.admin.AdminRepository;
import com.spring.holaeat.domain.food.Food;
import com.spring.holaeat.domain.food.FoodRequestDto;
import com.spring.holaeat.domain.health.Health;
import com.spring.holaeat.domain.ingredients.Ingredients;
import com.spring.holaeat.domain.ingredients.IngredientsRequestDto;
import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRepository;
import com.spring.holaeat.domain.review.ReviewResponseDto;
import com.spring.holaeat.service.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@SessionAttributes("authority")
@RequiredArgsConstructor
@Controller
public class AdminController {
    private final AdminRepository adminRepository;
    private final IngredientsService ingredientsService;
    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    private final ReviewCommentService reviewCommentService;
    private final FoodService foodService;
    private final HealthService healthService;

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
//            List<Food> list = foodService.getAllFood();
//            List<Food> list = foodService.getFoodWithoutFoodImg();
            List<Food> list = foodService.findWithoutImage();

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
    @ResponseBody
    @PostMapping("adminMenu/{foodId}")
    public FoodRequestDto updateFood(
            @PathVariable String foodId,
            @ModelAttribute FoodRequestDto foodRequestDto
    ) {
        Food food = foodService.findFoodByFoodId(foodId);

        if (foodRequestDto.getFoodImg() != null && !foodRequestDto.getFoodImg().isEmpty()) {
            try {
                byte[] imgBytes = foodRequestDto.getFoodImg().getBytes();
                foodService.updateFoodWithImage(food, foodRequestDto, imgBytes);
            } catch (IOException e) {
            }
        } else {
            foodService.update(food, foodRequestDto);
        }

        return foodRequestDto;
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

    @ResponseBody
    @GetMapping("adminReviewList")
    public Map getReview(@RequestParam(required = false) String page, @PageableDefault Pageable pageable) {
        int reqPage = (page == null || page.equals("")) ? 0 : Integer.parseInt(page);
        reqPage = reqPage < 0 ? 0 : reqPage;

        List<Review> reviewList = reviewRepository.findAll(pageable.withPage(reqPage)).getContent();

        if(reviewList.isEmpty()) {
            int count = (int) reviewRepository.count();
            reqPage = count%10 > 0 ? count/10 : count/10-1;
            System.out.println("last : " + reqPage);
            reviewList = reviewRepository.findAll(pageable.withPage(reqPage)).getContent();
        }

        List<ReviewResponseDto> responseList = ReviewResponseDto.parse(reviewList);

        JSONObject result = new JSONObject();
        result.put("page", reqPage);
        result.put("data", responseList);

        return result.toMap();
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
