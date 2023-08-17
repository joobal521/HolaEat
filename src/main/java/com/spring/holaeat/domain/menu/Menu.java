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

    @Column(name = "main")
    private String main;

    @Column(name = "main2")
    private String main2;

    @Transient // DB와 연결되지 않는 필드를 나타냄
    private int food1Weight;

    @Transient
    private int food2Weight;

    @Transient
    private int food3Weight;

    @Transient
    private int food4Weight;

    @Transient
    private int food5Weight;

    @Transient
    private Double food1Kcal;

    @Transient
    private Double food2Kcal;

    @Transient
    private Double food3Kcal;

    @Transient
    private Double food4Kcal;

    @Transient
    private Double food5Kcal;

    @Transient
    private int food1Carb;

    @Transient
    private int food1Protein;

    @Transient
    private int food1Fat;

    @Transient
    private int food2Carb;

    @Transient
    private int food2Protein;

    @Transient
    private int food2Fat;


    @Transient
    private int food3Carb;

    @Transient
    private int food3Protein;

    @Transient
    private int food3Fat;

    @Transient
    private int food4Carb;

    @Transient
    private int food4Protein;

    @Transient
    private int food4Fat;

    @Transient
    private int food5Carb;

    @Transient
    private int food5Protein;

    @Transient
    private int food5Fat;

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


    public void setFood1Weight(int food1Weight) {
        this.food1Weight = food1Weight;
    }

    public void setFood2Weight(int food2Weight) {
        this.food2Weight = food2Weight;
    }

    public void setFood3Weight(int food3Weight) {
        this.food3Weight = food3Weight;
    }


    public void setFood4Weight(int food4Weight) {
        this.food4Weight = food4Weight;
    }

    public void setFood5Weight(int food5Weight) {
        this.food5Weight = food5Weight;
    }

    public void setFood1Kcal(Double food1Kcal) {
        this.food1Kcal = food1Kcal;
    }

    public void setFood2Kcal(Double food2Kcal) {
        this.food2Kcal = food2Kcal;
    }

    public void setFood3Kcal(Double food3Kcal) {
        this.food3Kcal = food3Kcal;
    }

    public void setFood4Kcal(Double food4Kcal) {
        this.food4Kcal = food4Kcal;
    }

    public void setFood5Kcal(Double food5Kcal) {
        this.food5Kcal = food5Kcal;
    }

    public void setFood1Carb(int food1Carb) {
        this.food1Carb = food1Carb;
    }

    public void setFood1Protein(int food1Protein) {
        this.food1Protein = food1Protein;
    }
    public void setFood1Fat(int food1Fat) {
        this.food1Fat = food1Fat;
    }

    public void setFood2Carb(int food2Carb) {
        this.food2Carb = food2Carb;
    }

    public void setFood2Protein(int food2Protein) {
        this.food2Protein = food2Protein;
    }
    public void setFood2Fat(int food2Fat) {
        this.food2Fat = food2Fat;
    }
    public void setFood3Carb(int food3Carb) {
        this.food3Carb = food3Carb;
    }

    public void setFood3Protein(int food3Protein) {
        this.food3Protein = food3Protein;
    }
    public void setFood3Fat(int food3Fat) {
        this.food3Fat = food3Fat;
    }
    public void setFood4Carb(int food4Carb) {
        this.food4Carb = food4Carb;
    }

    public void setFood4Protein(int food4Protein) {
        this.food4Protein = food4Protein;
    }
    public void setFood4Fat(int food4Fat) {
        this.food4Fat = food4Fat;
    }
    public void setFood5Carb(int food5Carb) {
        this.food5Carb = food5Carb;
    }

    public void setFood5Protein(int food5Protein) {
        this.food5Protein = food5Protein;
    }
    public void setFood5Fat(int food5Fat) {
        this.food5Fat = food5Fat;
    }

}
