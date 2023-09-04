package com.spring.holaeat.domain.ingredients;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientsRepository extends JpaRepository<Ingredients,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM ingredients WHERE month = 1")
    List<Ingredients> findByMonthEquals();

    Ingredients findByIngrName(String ingrName); // 이 부분을 추가합니다.

    @Query(nativeQuery = true,value = "SELECT ingr_id, allergy, ingr_name, month FROM ingredients")
    List<Ingredients> findAllButImage();

    List<Ingredients> findAll();

    void deleteIngredientsByIngrId(int ingrId);

    Ingredients findByIngrId(int id);

    List<Ingredients> findAllByOrderByIngrIdDesc(Pageable pageable);


}
