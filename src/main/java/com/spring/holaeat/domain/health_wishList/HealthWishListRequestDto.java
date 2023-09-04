package com.spring.holaeat.domain.health_wishList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class HealthWishListRequestDto {
    Long healthWishNo;
    Long healthNo;
    String userId;


}
