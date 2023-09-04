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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_wish_no")
    Long healthWishNo;
    @JoinColumn(name = "health_no")
    Long healthNo;
    @JoinColumn(name = "user_id")
    private String userId;


    public HealthWishList(HealthWishListRequestDto healthWishListDto){
        this.healthNo=healthWishListDto.healthNo;
        this.userId= healthWishListDto.userId;
    }


}
