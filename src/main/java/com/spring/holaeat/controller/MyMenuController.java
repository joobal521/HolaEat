package com.spring.holaeat.controller;

import com.spring.holaeat.domain.user_menu.UserMenu;
import com.spring.holaeat.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyMenuController {

    @Autowired
    private UserMenuService userMenuService; // UserMenuService 주입

    @GetMapping("/myMenu")
    public String showMyMenu(@RequestParam String userId, Model model) {
        try {
            // 사용자의 메뉴 정보 조회
            List<UserMenu> userMenus = userMenuService.getUserMenus(userId);

            // 조회된 메뉴 정보를 모델에 저장
            model.addAttribute("userMenus", userMenus);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리
        }

        return "myMenu"; // myMenu.jsp로 포워딩
    }

}
