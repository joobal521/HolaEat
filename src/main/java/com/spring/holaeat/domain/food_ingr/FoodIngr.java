package com.spring.holaeat.domain.food_ingr;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class FoodIngr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodIngrId; // 엔티티 클래스에는 @Id 어노테이션을 사용한 식별자 필드가 있어야 합니다.

    private String foodId;
    private String ingrId;

    // 생성자, 게터/세터 등 필요한 코드 작성
}
