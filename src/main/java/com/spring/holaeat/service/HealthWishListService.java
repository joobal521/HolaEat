package com.spring.holaeat.service;


import com.spring.holaeat.domain.health_wishList.HealthWishList;
import com.spring.holaeat.domain.health_wishList.HealthWishListRepository;
import com.spring.holaeat.domain.health_wishList.HealthWishListRequestDto;
import com.spring.holaeat.exception.BusinessLogicException;
import com.spring.holaeat.exception.ExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HealthWishListService {
    private final HealthWishListRepository healthWishListRepository;


    @Autowired
    public HealthWishListService(HealthWishListRepository healthWishListRepository) {
        this.healthWishListRepository = healthWishListRepository;
    }


    //게시물 찜하기
    public void createHealthWishList(HealthWishListRequestDto healthWishListDto){
        Optional<HealthWishList> existingWishList = healthWishListRepository
                .findHealthWishListByUserIdAndHealthNo(healthWishListDto.getUserId(), healthWishListDto.getHealthNo());

        HealthWishList healthWishList=new HealthWishList(healthWishListDto);

        //이미 찜한 게시물
        if (existingWishList.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.WISHLIST_EXISTS);
        }


    }



}
