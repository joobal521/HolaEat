package com.spring.holaeat.domain.dislike;

import com.spring.holaeat.domain.ingredients.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DislikeRepository extends JpaRepository<Dislike, Long> {
    // 추가적인 메소드 정의...

    // userId와 ingrId를 이용한 조회
    Dislike findByUserIdAndIngrId(String userId, int ingrId);
}
