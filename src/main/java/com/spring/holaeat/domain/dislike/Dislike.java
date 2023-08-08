package com.spring.holaeat.domain.dislike;

import lombok.Getter;

import javax.persistence.Entity;

@Getter
public class Dislike {

    private String userId;
    private String ingrId;

}
