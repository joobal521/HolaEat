package com.spring.holaeat.domain.profile;

import com.spring.holaeat.util.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor
@Getter
@Table(name = "user_profile")
@Entity
public class ProfileImg extends Timestamp {

    @Id
    private long profileNo;

    @JoinColumn(name = "userId", nullable = false)
    private String userId;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] profileImg;


    //생성자
    public ProfileImg(ProfileImgRequestDto profileImgDto){
        this.profileNo=profileImgDto.getProfileNo();
        this.userId=profileImgDto.getUserId();
        if(profileImgDto.getProfileImg()!=null){
            try {
                this.profileImg = profileImgDto.getProfileImg().getBytes();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    //기능 메소드
    public void update(ProfileImgRequestDto profileImgDto){

        if(profileImgDto.getProfileImg()!=null){
                try {
                    this.profileImg = profileImgDto.getProfileImg().getBytes();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
              System.out.println("기본 이미지");
            }
        }
    }






