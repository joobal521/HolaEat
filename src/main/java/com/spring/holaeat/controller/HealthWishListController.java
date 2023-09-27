package com.spring.holaeat.controller;


import com.spring.holaeat.domain.health_wishList.HealthWishListRequestDto;
import com.spring.holaeat.payload.Response;
import com.spring.holaeat.service.HealthWishListService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


@SessionAttributes("log")
@RestController
public class HealthWishListController {

    private final HealthWishListService healthWishListService;


    public HealthWishListController(HealthWishListService healthWishListService) {
        this.healthWishListService = healthWishListService;
    }


    //게시물 찜하기
    @PostMapping(value = "healthWish/{healthNo}")
    public Response wishHealth(@PathVariable long healthNo, @ModelAttribute HealthWishListRequestDto healthWishListDto,  WebRequest request){

             healthWishListService.saveHealthWishList(healthWishListDto);

             return new Response("wishList","success");


    }



}
