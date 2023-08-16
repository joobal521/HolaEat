package com.spring.holaeat.service;

import com.spring.holaeat.domain.menu.Menu;
import com.spring.holaeat.domain.menu.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> generateMenuWithFoodNames() {
        List<Object[]> result = menuRepository.getMenuWithFoodNames();
        List<Menu> generatedMenuList = new ArrayList<>();

        for (Object[] row : result) {
            Menu menu = (Menu) row[0];
            String food1Name = (String) row[1];
            String food2Name = (String) row[2];
            String food3Name = (String) row[3];
            String food4Name = (String) row[4];
            String food5Name = (String) row[5];

            // 음식 이름을 해당 필드에 설정
            menu.setFood1Name(food1Name);
            menu.setFood2Name(food2Name);
            menu.setFood3Name(food3Name);
            menu.setFood4Name(food4Name);
            menu.setFood5Name(food5Name);

            generatedMenuList.add(menu);
        }

        return generatedMenuList;
    }
}
