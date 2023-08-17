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

    public List<Menu> getMenuWithFoodNamesAndWeights() {
        List<Object[]> result = menuRepository.getMenuWithFoodNamesAndWeights();
        List<Menu> generatedMenuList = new ArrayList<>();

        for (Object[] row : result) {
            System.out.println("Row length: " + row.length);

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
            Double food1Kcal = (Double) row[11]; // 첫 번째 음식 칼로리
            Double food2Kcal = (Double) row[12]; // 두 번째 음식 칼로리
            Double food3Kcal = (Double) row[13]; // 세 번째 음식 칼로리
            Double food4Kcal = (Double) row[14]; // 네 번째 음식 칼로리
            Double food5Kcal = (Double) row[15]; // 다섯 번째 음식 칼로리
            int food1Carb = (int) row[16];
            int food1Protein = (int) row[17];
            int food1Fat = (int) row[18];

            int food2Carb = (int) row[19];
            int food2Protein = (int) row[20];
            int food2Fat = (int) row[21];

            int food3Carb = (int) row[22];
            int food3Protein = (int) row[23];
            int food3Fat = (int) row[24];

            int food4Carb = (int) row[25];
            int food4Protein = (int) row[26];
            int food4Fat = (int) row[27];

            int food5Carb = (int) row[28];
            int food5Protein = (int) row[29];
            int food5Fat = (int) row[30];


            // ... (음식3, 음식4, 음식5 무게 가져오기)

            // 음식 이름과 무게, 칼로리를 해당 필드에 설정
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
            menu.setFood1Kcal(food1Kcal); // 음식1의 칼로리 설정
            menu.setFood2Kcal(food2Kcal); // 음식2의 칼로리 설정
            menu.setFood3Kcal(food3Kcal); // 음식3의 칼로리 설정
            menu.setFood4Kcal(food4Kcal); // 음식4의 칼로리 설정
            menu.setFood5Kcal(food5Kcal);

            menu.setFood1Carb(food1Carb);
            menu.setFood1Protein(food1Protein);
            menu.setFood1Fat(food1Fat);

            menu.setFood2Carb(food2Carb);
            menu.setFood2Protein(food2Protein);
            menu.setFood2Fat(food2Fat);

            menu.setFood3Carb(food3Carb);
            menu.setFood3Protein(food3Protein);
            menu.setFood3Fat(food3Fat);

            menu.setFood4Carb(food4Carb);
            menu.setFood4Protein(food4Protein);
            menu.setFood4Fat(food4Fat);

            menu.setFood5Carb(food5Carb);
            menu.setFood5Protein(food5Protein);
            menu.setFood5Fat(food5Fat);


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

