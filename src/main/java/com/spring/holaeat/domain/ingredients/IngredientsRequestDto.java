package com.spring.holaeat.domain.ingredients;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class IngredientsRequestDto {
    private int ingrId;
    private String ingrName;
    private Boolean allergy;
    private Boolean month;
//    private byte[] ingrImg;
    private MultipartFile ingrImg;

}
