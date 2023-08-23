package com.spring.holaeat.domain.profile;

import com.spring.holaeat.domain.user.User;
import com.spring.holaeat.util.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor
@Getter
@Table(name = "user_profile")
@Entity
public class ProfileImg extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 또는 다른 적절한 ID 생성 전략 선택
    private Long profileNo;


    @JoinColumn(name = "userId", nullable = false)
    private String userId;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] profileImg;


    // 기본 이미지 리소스 경로
    private static final String DEFAULT_IMAGE_PATH = "static/img/belle.jpg";



// 기본 이미지 URL 반환
//    public String getDefaultImageUrl() {
//        return DEFAULT_IMAGE_PATH;
//    }

    // 생성자
    public ProfileImg(ProfileImgRequestDto profileImgDto, String userId) {
        this.profileNo = profileImgDto.getProfileNo();
        this.userId = userId;

        if (profileImgDto.getProfileImg() != null) {
            try {
                this.profileImg = profileImgDto.getProfileImg().getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.profileImg = getDefaultImageBytes(); // 기본 이미지로 설정
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // 기능 메소드
    public void update(ProfileImgRequestDto profileImgDto) {
        if (profileImgDto.getProfileImg() != null) {
            try {
                this.profileImg = profileImgDto.getProfileImg().getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
          this.profileImg=null;
        }
    }

    // 기본 이미지 바이트 배열 반환
    private byte[] getDefaultImageBytes() throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(DEFAULT_IMAGE_PATH)) {
            if (is != null) {
                return is.readAllBytes();
            }
            throw new IOException("기본 이미지 파일을 읽을 수 없습니다.");
        }
    }

    // 프로필 이미지 바이트 배열 반환
    public byte[] getProfileImgBytes() {
        return profileImg;
    }
}





