package com.spring.holaeat.domain.user;

import com.spring.holaeat.domain.user_detail.UserDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
        private String userId;
        private String userName;
        private String userEmail;
        private Integer userAge;
        private int userHeight;
        private int userWeight;
        private Double userRecCalories;
        private String userAllergy;
        private String userGender;
        private String userPrefer;
        private String userDislike;

        public UserResponseDto(User user, UserDetail userDetail) {
            this.userId = user.getUserId();
            this.userName = user.getUserName();
            this.userEmail = user.getUserEmail();
            this.userAge = userDetail.getAge();
            this.userHeight = userDetail.getHeight();
            this.userWeight = userDetail.getWeight();
            this.userRecCalories = userDetail.getRecCalories();
            this.userAllergy = userDetail.getAllergy();
            this.userGender = userDetail.getGender();
            this.userPrefer = userDetail.getPrefer();
            this.userDislike = userDetail.getDislike();
        }

    private UserDetail userDetail; // UserDetail 정보를 저장할 필드

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
        if (userDetail != null) {
            this.userAge = userDetail.getAge();
            this.userHeight = userDetail.getHeight();
            this.userWeight = userDetail.getWeight();
            this.userRecCalories = userDetail.getRecCalories();
            this.userAllergy = userDetail.getAllergy();
            this.userGender = userDetail.getGender();
            this.userPrefer = userDetail.getPrefer();
            this.userDislike = userDetail.getDislike();
        }
    }
    // 필요한 게터 메서드들
}
