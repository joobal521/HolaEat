package com.spring.holaeat.service;

import com.spring.holaeat.domain.user_menu.UserMenu;
import com.spring.holaeat.domain.user_menu.UserMenuRepository;
import com.spring.holaeat.domain.user_menu.UserMenuRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMenuService {

    @Autowired
    private UserMenuRepository userMenuRepository;

    public void saveSelectedMenus(UserMenuRequestDto userMenuRequestDto) {
        String userId = userMenuRequestDto.getUserId();
        List<Long> selectedMenuNos = userMenuRequestDto.getSelectedMenuNos();

        for (Long menuNo : selectedMenuNos) {
            UserMenu userMenu = new UserMenu();
            userMenu.setUserId(userId);
            userMenu.setMenuNo(menuNo);
            userMenuRepository.save(userMenu);
        }
    }

        public List<UserMenu> getUserMenus(String userId) {
            // 사용자 ID에 해당하는 메뉴 정보 조회 및 반환
            return userMenuRepository.findByUserId(userId);
        }


}
