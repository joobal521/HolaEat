package com.spring.holaeat.domain.dislike;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DislikeRequestDto {
    private String user_id;
    private String ingr_id;
}
