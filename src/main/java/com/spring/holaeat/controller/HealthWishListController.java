package com.spring.holaeat.controller;


import com.spring.holaeat.domain.health_wishList.HealthWishList;
import com.spring.holaeat.domain.health_wishList.HealthWishListRepository;
import com.spring.holaeat.domain.health_wishList.HealthWishListRequestDto;
import com.spring.holaeat.payload.Response;
import com.spring.holaeat.service.HealthWishListService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;


@SessionAttributes("log")
@RestController
public class HealthWishListController {

    private final HealthWishListRepository healthWishListRepository;
    private final HealthWishListService healthWishListService;


    public HealthWishListController(HealthWishListRepository healthWishListRepository, HealthWishListService healthWishListService) {
        this.healthWishListRepository = healthWishListRepository;
        this.healthWishListService = healthWishListService;
    }


    //게시물 찜하기
    @PostMapping(value = "healthWish/{healthNo}")
    public Map wishHealth(@PathVariable long healthNo, @ModelAttribute HealthWishListRequestDto healthWishListDto, WebRequest request){
            JSONObject response =new JSONObject();
           // HealthWishList healthWishList=healthWishListRepository.findByHealthWishNo()

            try{
                healthWishListService.saveHealthWishList(healthWishListDto);
                System.out.println("찜하기");
                response.put("result", true);

            }catch (IllegalArgumentException e){
                response.put("result", false);

            }


             return  response.toMap();


    }



}
