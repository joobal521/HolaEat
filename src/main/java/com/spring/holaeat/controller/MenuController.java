package com.spring.holaeat.controller;

import com.spring.holaeat.domain.menu.Menu;
import com.spring.holaeat.service.MenuService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Menu>> generateMenu() {
        List<Menu> generatedMenus = menuService.getMenuWithFoodNamesAndWeights(); // 변경된 메서드 호출
        return ResponseEntity.ok(generatedMenus);
    }

}