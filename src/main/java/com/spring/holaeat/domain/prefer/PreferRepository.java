package com.spring.holaeat.domain.prefer;

import com.spring.holaeat.domain.ingredients.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferRepository extends JpaRepository<Prefer, Integer> {

    // userId와 ingrId를 이용한 조회
    Prefer findByUserIdAndIngrId(String userId, int ingrId);
}
