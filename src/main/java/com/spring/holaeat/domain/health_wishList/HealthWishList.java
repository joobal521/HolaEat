package com.spring.holaeat.domain.health_wishList;


import com.spring.holaeat.domain.health.HealthRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "health_wishList")
public class HealthWishList {

    @Id
    @Column(name = "health_wish_no")
    Long HealthWishNo;
    @Column(name = "health_no")
    Long HealthNo;
    @Column(name = "user_id")
    private String UserId;


    public HealthWishList(HealthWishListRequestDto healthWishListDto){
        this.HealthWishNo=healthWishListDto.HealthWishNo;
        this.HealthNo=healthWishListDto.HealthNo;
        this.UserId= healthWishListDto.UserId;
    }


}
