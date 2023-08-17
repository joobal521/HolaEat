package com.spring.holaeat.domain.health;

import lombok.Getter;

@Getter
public class HealthResponseDto {
private Long healthNo;
private String id;

private String title;
private String content;

    public HealthResponseDto(Health entity) {
        this.healthNo = entity.getHealthNo();
        this.id=entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }


}
