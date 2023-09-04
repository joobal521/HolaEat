package com.spring.holaeat.domain.health_wishList;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HealthWishListRepository extends JpaRepository<HealthWishList, Long> {

    Optional<HealthWishList> findHealthWishListByUserIdAndHealthNo(String userId, Long HealthNo);

}
