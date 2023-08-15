package com.spring.holaeat.domain.ingredients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientsRepository extends JpaRepository<Ingredients,String> {

    @Query(nativeQuery = true, value = "SELECT * FROM ingredients WHERE month = 1")
    List<Ingredients> findByMonthEquals();

    Ingredients findByIngrName(String ingrName); // 이 부분을 추가합니다.

    List<Ingredients> findAll();

    void deleteIngredientsByIngrId(String ingrId);

    Ingredients findByIngrId(String id);




}
