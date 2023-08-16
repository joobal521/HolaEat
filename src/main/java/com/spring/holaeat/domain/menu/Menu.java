package com.spring.holaeat.domain.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Menu {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private Long no;

    @Column(name = "menu_id")
    private int menuId;

    @Column(name = "allergy")
    private int allergy;

    @Column(name = "food1")
    private String food1;

    @Column(name = "food2")
    private String food2;

    @Column(name = "food3")
    private String food3;

    @Column(name = "food4")
    private String food4;

    @Column(name = "food5")
    private String food5;

    @Column(name = "main1")
    private String main1;

    @Column(name = "main2")
    private String main2;

    public void setFood1Name(String food1Name) {
        this.food1 = food1Name;
    }
    public void setFood2Name(String food2Name) {
        this.food2 = food2Name;
    }
    public void setFood3Name(String food3Name) {
        this.food3 = food3Name;
    }
    public void setFood4Name(String food4Name) {
        this.food4 = food4Name;
    }
    public void setFood5Name(String food5Name) {
        this.food5 = food5Name;
    }


}
