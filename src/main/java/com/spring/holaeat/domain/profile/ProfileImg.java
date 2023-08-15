package com.spring.holaeat.domain.profile;

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
    private Long profileNo;

    @JoinColumn(name = "userId", nullable = false)
    private String userId;

    @Lob
    private byte[] profileImg;

    // 기본 이미지 리소스 경로
    private static final String DEFAULT_IMAGE_PATH = "classpath:static/img/belle.jpg";

    private byte[] getDefaultImageBytes() throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("static/img/belle.jpg")) {
            if (is != null) {
                return is.readAllBytes();
            }
            throw new IOException("기본 이미지 파일을 읽을 수 없습니다.");
        }
    }




// 기본 이미지 URL 반환
    public String getDefaultImageUrl() {
        return DEFAULT_IMAGE_PATH;
    }

    // 생성자
    public ProfileImg(ProfileImgRequestDto profileImgDto) {
        this.profileNo = profileImgDto.getProfileNo();
        this.userId = profileImgDto.getUserId();
        if (profileImgDto.getProfileImg() != null) {
            try {
                this.profileImg = profileImgDto.getProfileImg().getBytes();
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
            try {
                this.profileImg = getDefaultImageBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("기본 이미지");
        }
    }
}
