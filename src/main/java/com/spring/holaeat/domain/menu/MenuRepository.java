package com.spring.holaeat.domain.menu;

import com.spring.holaeat.domain.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, String> {

    @Query("SELECT m, f1.foodName, f2.foodName, f3.foodName, f4.foodName, f5.foodName FROM Menu m " +
            "JOIN Food f1 ON m.food1 = f1.foodId " +
            "JOIN Food f2 ON m.food2 = f2.foodId " +
            "JOIN Food f3 ON m.food3 = f3.foodId " +
            "JOIN Food f4 ON m.food4 = f4.foodId " +
            "JOIN Food f5 ON m.food5 = f5.foodId")
    List<Object[]> getMenuWithFoodNames();
}
