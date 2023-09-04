package com.spring.holaeat.service;


import com.spring.holaeat.domain.health_wishList.HealthWishList;
import com.spring.holaeat.domain.health_wishList.HealthWishListRepository;
import com.spring.holaeat.domain.health_wishList.HealthWishListRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthWishListService {
    private final HealthWishListRepository healthWishListRepository;


    @Autowired
    public HealthWishListService(HealthWishListRepository healthWishListRepository) {
        this.healthWishListRepository = healthWishListRepository;
    }


    //게시물 찜하기
    public void createHealthWishList(HealthWishListRequestDto healthWishListDto){
        HealthWishList healthWishList=new HealthWishList(healthWishListDto);

        //이미 찜한 게시물
        

    }



}
