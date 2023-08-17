package com.spring.holaeat.domain.menu;

import com.spring.holaeat.domain.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, String> {

    @Query("SELECT m, f1.foodName, f2.foodName, f3.foodName, f4.foodName, f5.foodName, " +
            "f1.foodWeight, f2.foodWeight, f3.foodWeight, f4.foodWeight, f5.foodWeight, " +
            "f1.kcal, f2.kcal, f3.kcal, f4.kcal, f5.kcal,f1.carb, f2.carb, f3.carb, f4.carb, f5.carb, f1.protein, f2.protein," +
            "f3.protein, f4.protein, f5.protein, f1.fat, f2.fat, f3.fat, f4.fat, f5.fat " +
            "FROM Menu m " +
            "JOIN Food f1 ON m.food1 = f1.foodId " +
            "JOIN Food f2 ON m.food2 = f2.foodId " +
            "JOIN Food f3 ON m.food3 = f3.foodId " +
            "JOIN Food f4 ON m.food4 = f4.foodId " +
            "JOIN Food f5 ON m.food5 = f5.foodId")
    List<Object[]> getMenuWithFoodNamesAndWeights();







}
