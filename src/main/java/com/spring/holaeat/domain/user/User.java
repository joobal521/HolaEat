package com.spring.holaeat.domain.user;

import com.spring.holaeat.domain.profile.ProfileImg;
import com.spring.holaeat.domain.user_detail.UserDetail;
import com.spring.holaeat.util.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Table(name = "user")
@Entity
public class User extends Timestamp {

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserDetail userDetail;

    public UserDetail getUserDetail() {
        return userDetail;
    }

    @Id
    private String userId;
    private String userPassword;
    private String userName;
    @Column(unique = true)
    private String userEmail;




    //생성자
    public User(UserRequestDto userDto) {
        this.userId = userDto.getUserId();
        this.userPassword=userDto.getUserPassword();
        this.userName=userDto.getUserName();
        this.userEmail=userDto.getUserEmail();

    }

    //회원 정보 수정 기능 메소드
    public void update(UserRequestDto userDto){
        this.userPassword=userDto.getUserPassword();
        this.userEmail=userDto.getUserEmail();
        this.userName = userDto.getUserName();

    }

    //비밀 번호 찾기 기능 메소드
    public void updatePwd(UserRequestDto userDto){
        this.userPassword=userDto.getUserPassword();
    }








}
