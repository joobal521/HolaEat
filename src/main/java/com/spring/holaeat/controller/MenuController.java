package com.spring.holaeat.controller;

import com.spring.holaeat.domain.menu.Menu;
import com.spring.holaeat.domain.user_menu.UserMenuRequestDto;
import com.spring.holaeat.service.MenuService;
import com.spring.holaeat.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    private UserMenuService userMenuService;

    @PostMapping("/saveSelectedMenus")
    public ResponseEntity<String> saveSelectedMenus(@RequestBody UserMenuRequestDto userMenuRequestDto) {
        try {
            userMenuService.saveSelectedMenus(userMenuRequestDto);
            return ResponseEntity.ok("Selected menus saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving selected menus.");
        }
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Menu>> generateMenu() {
        List<Menu> generatedMenus = menuService.getMenuWithFoodNamesAndWeights(); // 변경된 메서드 호출
        return ResponseEntity.ok(generatedMenus);
    }

}