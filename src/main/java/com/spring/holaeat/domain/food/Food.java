package com.spring.holaeat.domain.food;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Base64;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "food")
public class Food {

    @Id
    private String foodId;
    private String foodNational;
    private String foodGroup;
    private String foodName;
    private int foodWeight;
    private boolean allergyInfo;
    private boolean vegan;
    private boolean weightControl;
    private boolean balanced;
    private boolean sideDish;
    private byte[] foodImg;

    private Double kcal;
    private int carb;
    private int protein;
    private int fat;
    private int sugars;
    private int natrium;


    public void remainImg(byte[] img){
        this.foodImg = img;
    }

    public void update(FoodRequestDto foodRequestDto){
        this.foodNational = foodRequestDto.getFoodNational();
        this.foodGroup = foodRequestDto.getFoodGroup();
        this.foodName = foodRequestDto.getFoodName();
        this.foodWeight = foodRequestDto.getFoodWeight();
        this.vegan = foodRequestDto.isVegan();
        this.allergyInfo = foodRequestDto.isAllergyInfo();
        this.weightControl = foodRequestDto.isWeightControl();
        this.balanced = foodRequestDto.isBalanced();
        this.sideDish = foodRequestDto.isSideDish();
        this.carb =foodRequestDto.getCarb();
        this.protein = foodRequestDto.getProtein();
        this.fat = foodRequestDto.getFat();
        this.natrium = foodRequestDto.getNatrium();
        this.sugars = foodRequestDto.getSugars();
        this.kcal = foodRequestDto.getKcal();

    }

    public Food(String foodId, String foodNational, String foodGroup, String foodName, int foodWeight, boolean allergyInfo, boolean vegan, boolean weightControl, boolean balanced, boolean sideDish, byte[] foodImg, Double kcal, Integer carb, Integer protein, Integer fat, Integer sugars, Integer natrium) {
        this.foodId = foodId;
        this.foodNational = foodNational;
        this.foodGroup = foodGroup;
        this.foodName = foodName;
        this.foodWeight = foodWeight;
        this.allergyInfo = allergyInfo;
        this.vegan = vegan;
        this.weightControl = weightControl;
        this.balanced = balanced;
        this.sideDish = sideDish;
        this.foodImg = foodImg;
        this.kcal = kcal;
        this.carb = carb;
        this.protein = protein;
        this.fat = fat;
        this.sugars = sugars;
        this.natrium = natrium;
    }

    public Food(String foodId, String foodNational, String foodGroup, String foodName, int foodWeight, boolean allergyInfo, boolean vegan, boolean weightControl, boolean balanced, boolean sideDish, Double kcal, Integer carb, Integer protein, Integer fat, Integer sugars, Integer natrium) {
        this.foodId = foodId;
        this.foodNational = foodNational;
        this.foodGroup = foodGroup;
        this.foodName = foodName;
        this.foodWeight = foodWeight;
        this.allergyInfo = allergyInfo;
        this.vegan = vegan;
        this.weightControl = weightControl;
        this.balanced = balanced;
        this.sideDish = sideDish;
        this.kcal = kcal;
        this.carb = carb;
        this.protein = protein;
        this.fat = fat;
        this.sugars = sugars;
        this.natrium = natrium;
    }


    public Food(FoodRequestDto foodRequestDto){
        this.foodId = foodRequestDto.getFoodId();
        this.foodNational = foodRequestDto.getFoodNational();
        this.foodGroup = foodRequestDto.getFoodGroup();
        this.foodName = foodRequestDto.getFoodName();
        this.foodWeight = foodRequestDto.getFoodWeight();
        this.allergyInfo = foodRequestDto.isAllergyInfo();
        this.vegan = foodRequestDto.isVegan();
        this.weightControl = foodRequestDto.isWeightControl();
        this.balanced = foodRequestDto.isBalanced();
        this.sideDish = foodRequestDto.isSideDish();
        this.kcal = foodRequestDto.getKcal();
        this.carb = foodRequestDto.getCarb();
        this.protein = foodRequestDto.getProtein();
        this.fat = foodRequestDto.getFat();
        this.sugars = foodRequestDto.getSugars();
        this.natrium = foodRequestDto.getNatrium();
    }

}
