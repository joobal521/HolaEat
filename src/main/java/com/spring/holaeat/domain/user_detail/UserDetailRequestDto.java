package com.spring.holaeat.domain.user_detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailRequestDto {
    private String userId;
    private String gender;
    private int age;
    private Integer weight;
    private Integer height;
    private String allergy;
    private Double recCalories;



    // Constructors, getters, setters, etc.
}

