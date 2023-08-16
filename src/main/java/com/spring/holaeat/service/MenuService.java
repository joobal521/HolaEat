package com.spring.holaeat.service;

import com.spring.holaeat.domain.menu.Menu;
import com.spring.holaeat.domain.menu.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> generateMenuWithFoodNamesAndWeights() {
        List<Object[]> result = menuRepository.getMenuWithFoodNamesAndWeights();
        List<Menu> generatedMenuList = new ArrayList<>();

        for (Object[] row : result) {
            Menu menu = (Menu) row[0];
            String food1Name = (String) row[1];
            String food2Name = (String) row[2];
            String food3Name = (String) row[3];
            String food4Name = (String) row[4];
            String food5Name = (String) row[5];
            int food1Weight = (int) row[6];
            int food2Weight = (int) row[7];
            int food3Weight = (int) row[8];
            int food4Weight = (int) row[9];
            int food5Weight = (int) row[10];
            // ... (음식3, 음식4, 음식5 무게 가져오기)

            // 음식 이름과 무게를 해당 필드에 설정
            menu.setFood1Name(food1Name);
            menu.setFood2Name(food2Name);
            menu.setFood3Name(food3Name);
            menu.setFood4Name(food4Name);
            menu.setFood5Name(food5Name);
            // ... (음식3, 음식4, 음식5도 설정)
            menu.setFood1Weight(food1Weight);
            menu.setFood2Weight(food2Weight);
            menu.setFood3Weight(food3Weight);
            menu.setFood4Weight(food4Weight);
            menu.setFood5Weight(food5Weight);
            // ... (음식3, 음식4, 음식5 무게도 설정)

            generatedMenuList.add(menu);
        }

        return generatedMenuList;
    }


//    public List<Menu> generateMenuWithFoodNames() {
//        List<Object[]> result = menuRepository.getMenuWithFoodNames();
//        List<Menu> generatedMenuList = new ArrayList<>();
//
//        for (Object[] row : result) {
//            Menu menu = (Menu) row[0];
//            String food1Name = (String) row[1];
//            String food2Name = (String) row[2];
//            String food3Name = (String) row[3];
//            String food4Name = (String) row[4];
//            String food5Name = (String) row[5];
//
//            // 음식 이름을 해당 필드에 설정
//            menu.setFood1Name(food1Name);
//            menu.setFood2Name(food2Name);
//            menu.setFood3Name(food3Name);
//            menu.setFood4Name(food4Name);
//            menu.setFood5Name(food5Name);
//
//            generatedMenuList.add(menu);
//        }
//
//        return generatedMenuList;
//    }
}

