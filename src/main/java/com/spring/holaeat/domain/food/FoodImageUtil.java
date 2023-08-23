package com.spring.holaeat.domain.food;

import java.util.Base64;

public class FoodImageUtil {
    public static String encodeFoodImageToBase64(byte[] foodImage) {
        return Base64.getEncoder().encodeToString(foodImage);
    }
}
