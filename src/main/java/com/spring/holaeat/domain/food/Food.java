package com.spring.holaeat.domain.food;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private byte[] foodimg;

    public Food(String foodId, String foodNational, String foodGroup, String foodName, int foodWeight, boolean allergyInfo, boolean vegan, boolean weightControl, boolean balanced, boolean sideDish, byte[] foodimg) {
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
        this.foodimg = foodimg;
    }
}
