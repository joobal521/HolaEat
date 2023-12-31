package com.spring.holaeat.controller;

import com.spring.holaeat.domain.admin.Admin;
import com.spring.holaeat.domain.admin.AdminRepository;
import com.spring.holaeat.domain.food.Food;
import com.spring.holaeat.domain.food.FoodRequestDto;
import com.spring.holaeat.domain.health.Health;
import com.spring.holaeat.domain.health.HealthRepository;
import com.spring.holaeat.domain.ingredients.Ingredients;
import com.spring.holaeat.domain.ingredients.IngredientsRequestDto;
import com.spring.holaeat.domain.ingredients.IngredientsResponseDto;
import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRepository;
import com.spring.holaeat.domain.review.ReviewResponseDto;
import com.spring.holaeat.service.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final HealthRepository healthRepository;
    private final ReviewLikeService reviewLikeService;

    //관리자 로그인
//@PostMapping("gainpower")
//public String gainPower(@RequestParam("adminid") String id, @RequestParam("adminpwd") String pwd, HttpSession session) {
//    List<Admin> admin = adminRepository.findAdminByIdAndPassword(id, pwd);
//
//    if (admin.isEmpty()) {
//        System.out.println("관리자 로그인 실패");
//        return "gainpower"; // 실패 시 해당 뷰로 반환
//    } else {
//        session.setAttribute("authority", "POWERED");
//        System.out.println("관리자 로그인");
//        return "redirect:/admin"; // 리다이렉트하여 주소창의 URL도 변경
//    }
//}
    @PostMapping("gainpower")
    public String gainPower(@RequestParam("adminid") String id, @RequestParam("adminpwd") String pwd, HttpSession session) {
        List<Admin> admin = adminRepository.findAdminByIdAndPassword(id, pwd);

        if (admin.isEmpty()) {
            System.out.println("관리자 로그인 실패");
        } else {
            session.setAttribute("authority", "POWERED");
            System.out.println("관리자 로그인");
        }
        return admin.isEmpty() ? "gainpower" : "redirect:/admin";
    }

    //재료관리------------------
    @GetMapping("adminIngr")
    public String getIngredients(Model model) {
        List<Ingredients> list = ingredientsService.getAllIngredients();
//        List<Ingredients> list = ingredientsService.getIngredientsButImage();
        model.addAttribute("ingredientList", list);
        return "adminIngr";
    }

    //재료정보생성
    @PostMapping(value = "adminIngr/create", consumes = "multipart/form-data")
    public ResponseEntity<String> addIngredient(@ModelAttribute IngredientsRequestDto ingredientsRequestDto) {

        try {
            // 파일이 첨부되었는지 확인
            if (ingredientsRequestDto.getIngrImg().isEmpty()) {
                return ResponseEntity.badRequest().body("사진을 첨부하세요.");
            }
            ingredientsService.addIngredient(ingredientsRequestDto);
            return ResponseEntity.ok("재료 추가 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 중 오류 발생");
        }

    }

    //재료정보수정
    @ResponseBody
    @PostMapping("adminIngr/{ingrId}")
    public IngredientsRequestDto updateIngredients(
            @PathVariable int ingrId,
            @ModelAttribute IngredientsRequestDto ingredientsRequestDto
    ) {
        Ingredients ingredients = ingredientsService.findById(ingrId);

        if (ingredientsRequestDto.getIngrImg() != null && !ingredientsRequestDto.getIngrImg().isEmpty()) {
            try {
                // StandardMultipartFile을 byte[]로 변환
                byte[] imgBytes = ingredientsRequestDto.getIngrImg().getBytes();
                ingredientsService.updateIngrWithImage(ingredients, ingredientsRequestDto, imgBytes);
            } catch (IOException e) {
                // 예외 처리
            }
        } else {
            ingredientsService.remainImg(ingredients, ingredients.getIngrImg());
            ingredientsService.update(ingredients, ingredientsRequestDto);
        }

        return ingredientsRequestDto;
    }

    //재료삭제
    @DeleteMapping("adminIngr/delete/{ingrId}")
    public ResponseEntity<String> deleteIngrByID(@PathVariable int ingrId) {
        try {
            ingredientsService.deleteIngredientsByIngrId(ingrId);
            return ResponseEntity.ok("재료 추가 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
        }
    }


    //메뉴관리--------------------
    @GetMapping("adminMenu")
    public String getAllMenu(Model model) {
        List<Food> list = foodService.getAllFood();
//            List<Food> list = foodService.getFoodWithoutFoodImg();
//            List<Food> list = foodService.getAllWithoutImg();
        model.addAttribute("foodList", list);
        return "adminMenu";
    }

    //음식 추가
    @PostMapping(value = "adminMenu/create", consumes = "multipart/form-data")
    public ResponseEntity<String> addFood(@ModelAttribute FoodRequestDto foodRequestDto) {
        try {
            foodService.addFood(foodRequestDto);
            return ResponseEntity.ok("음식 추가 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("추가 실패");
        }
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
            foodService.remainImg(food, food.getFoodImg());
            foodService.update(food, foodRequestDto);
        }

        return foodRequestDto;
    }

    @DeleteMapping("adminMenu/delete/{foodId}")
    public ResponseEntity<String> deleteFood(@PathVariable String foodId) {
        try {
            foodService.deleteFoodByFoodId(foodId);
            return ResponseEntity.ok("음식 삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
        }
    }

    //후기게시판관리-------------------
    @GetMapping("adminReview")
    public String getReview(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        int reviewsPerPage = 10;
        List<Review> reviewList = reviewService.getReviewsByPage(page, reviewsPerPage);
        model.addAttribute("reviewList", reviewList);
        return "adminReview";
    }

    //후기게시판관리 페이징
    @ResponseBody
    @GetMapping("adminReviewList")
    public Map getReview(@RequestParam(required = false) String page, @PageableDefault Pageable pageable) {

        int reqPage = (page == null || page.equals("")) ? 0 : Integer.parseInt(page);
        reqPage = reqPage < 0 ? 0 : reqPage;

        List<Review> reviewList = reviewRepository.findAll(pageable.withPage(reqPage)).getContent();

        if (reviewList.isEmpty()) {
            int count = (int) reviewRepository.count();
            reqPage = count % 10 > 0 ? count / 10 : count / 10 - 1;
            System.out.println("last : " + reqPage);
            reviewList = reviewRepository.findAll(pageable.withPage(reqPage)).getContent();
        }

        List<ReviewResponseDto> responseList = ReviewResponseDto.parse(reviewList);

        JSONObject result = new JSONObject();
        result.put("page", reqPage);
        result.put("data", responseList);

        return result.toMap();
    }

    //후기삭제
    @DeleteMapping("adminReview/delete/{reviewNo}")
    public ResponseEntity<String> deleteReview(@PathVariable long reviewNo) {
        try {
            reviewCommentService.deleteByReviewNo(reviewNo);
            reviewLikeService.deleteByReviewNo(reviewNo);
            reviewService.delete(reviewNo);
            return ResponseEntity.ok("후기삭제성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
        }
    }
//
//    //유저관리
//    @GetMapping("adminUser")
//    public String getUser(Model model){
//
//        return "adminUser";
//    }
//

    //건강정보관리-----------------
    @GetMapping("adminHealth/{pageNumber}")
    public String getHealth(@PathVariable int pageNumber, @PageableDefault(size = 4) Pageable pageable, Model model) {
        List<Health> healthList = null;
        Pageable adjustedPageable = PageRequest.of(pageNumber - 1, pageable.getPageSize(), pageable.getSort());
        healthList = healthRepository.findAllByOrderByHealthNoDesc(adjustedPageable);

        int totalLength = (int) healthRepository.count(); // 총 건강 정보 수 가져오기
        int totalPages = (int) Math.ceil((double) totalLength / pageable.getPageSize()); // 전체 페이지 수 계산

        System.out.println("totalLength :" + totalLength );
        System.out.println("totalPages :" + totalPages );
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", totalPages);



        model.addAttribute("adminHealth", healthList);
        return "adminHealth";
    }


}
