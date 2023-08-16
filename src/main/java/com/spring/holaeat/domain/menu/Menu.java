package com.spring.holaeat.domain.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Menu {

    @Id
    @JsonProperty("menuId")
    private String menuId;
    private int allergy;
    private String food1;
    private String food2;
    private String food3;
    private String food4;
    private String food5;
    private String main1;
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
