package com.spring.holaeat.domain.nutritions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NutritionsRepository extends JpaRepository<Nutritions,Long> {

    @Query(nativeQuery = true,value ="Select * From nutritions WHERE food_id = ?1")
    List<Nutritions> findByFoodId(String foodId);


    List<Nutritions>findAllByFoodId(String foodId);

    List<Nutritions> findAll();



}
