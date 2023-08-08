package com.spring.holaeat.domain.dislike;

import lombok.Getter;

import javax.persistence.Entity;

@Getter
public class Dislike {

    private String user_id;
    private String ingr_id;

}
