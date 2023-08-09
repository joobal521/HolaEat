package com.spring.holaeat.service;

import com.spring.holaeat.domain.user.User;
import com.spring.holaeat.domain.user.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCaloriesService {
    private final UserRepository userRepository;

    public UserCaloriesService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Getter
    @Setter
    public static class UserCaloriesRequestDto {
        private String userId;
        private double recCalories;

        public UserCaloriesRequestDto() {
            // 기본 생성자
        }

        public UserCaloriesRequestDto(String userId, double recCalories) {
            this.userId = userId;
            this.recCalories = recCalories;
        }
    }

//    @Transactional
//    public void updateUserCalories(String userId, double recCalories) {
//        User user = userRepository.findById(userId).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
//        );
//        user.setRecCalories(recCalories);
//        userRepository.save(user);
//    }
}
