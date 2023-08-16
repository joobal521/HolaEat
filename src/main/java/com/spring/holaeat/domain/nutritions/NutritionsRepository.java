package com.spring.holaeat.domain.nutritions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NutritionsRepository extends JpaRepository<Nutritions,Integer> {

    List<Nutritions> findByFoodId(String foodid);



}
