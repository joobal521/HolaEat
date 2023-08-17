package com.spring.holaeat.domain.menu;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class MenuRequestDto {

    private Long no;
    private int menuId;
    private int allergy;
    private String food1;
    private String food2;
    private String food3;
    private String food4;
    private String food5;
    private String main;
    private String main2;


}
