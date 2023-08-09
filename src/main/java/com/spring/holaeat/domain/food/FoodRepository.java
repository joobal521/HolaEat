package com.spring.holaeat.domain.food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,String> {

    @Query(nativeQuery = true,value = "SELECT * FROM food WHERE food_id = ?1")
    public List<Food> findFoodByFoodId(String foodId);

}
