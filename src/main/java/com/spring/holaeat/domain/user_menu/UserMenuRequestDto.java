package com.spring.holaeat.domain.user_menu;

import java.util.List;

public class UserMenuRequestDto {
    private String userId;
    private List<Long> selectedMenuNos;

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public List<Long> getSelectedMenuNos() {
        return selectedMenuNos;
    }

    // Other constructors, methods...
}
