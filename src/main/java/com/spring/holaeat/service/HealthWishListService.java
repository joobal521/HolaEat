package com.spring.holaeat.service;


import com.spring.holaeat.domain.health_wishList.HealthWishList;
import com.spring.holaeat.domain.health_wishList.HealthWishListRepository;
import com.spring.holaeat.domain.health_wishList.HealthWishListRequestDto;
import com.spring.holaeat.exception.BusinessLogicException;
import com.spring.holaeat.exception.ExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class HealthWishListService {
    private final HealthWishListRepository healthWishListRepository;


    @Autowired
    public HealthWishListService(HealthWishListRepository healthWishListRepository) {
        this.healthWishListRepository = healthWishListRepository;
    }


    //게시물 찜하기
    public void saveHealthWishList(HealthWishListRequestDto healthWishListDto){
        Optional<HealthWishList> existingWishList = healthWishListRepository
                .findHealthWishListByUserIdAndHealthNo(healthWishListDto.getUserId(), healthWishListDto.getHealthNo());

        HealthWishList healthWishList=new HealthWishList(healthWishListDto);


        //게시물이 찜 안돼 있을때
        if (existingWishList.isEmpty()) {
            healthWishListRepository.save(healthWishList);
            System.out.println("찜하기");
        }



    }

//게시물 찜 취소
    @Transactional
    public void deleteHealthWishList(HealthWishListRequestDto healthWishListDto){
        Optional<HealthWishList> existingWishList = healthWishListRepository
                .findHealthWishListByUserIdAndHealthNo(healthWishListDto.getUserId(), healthWishListDto.getHealthNo());

        HealthWishList healthWishList=new HealthWishList(healthWishListDto);

        //이미 찜한 게시물->찜 취소 처리
        if (existingWishList.isPresent()) {
            healthWishListRepository.delete(healthWishList);
            System.out.println("찜 취소");
        }

    }



}
