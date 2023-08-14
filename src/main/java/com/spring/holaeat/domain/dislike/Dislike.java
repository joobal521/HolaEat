package com.spring.holaeat.domain.dislike;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Dislike {
    @Id
    private String userId; // userId를 기본 키로 사용

    private String ingrId;
}