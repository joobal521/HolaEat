package com.spring.holaeat.domain.profile;

import com.spring.holaeat.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfileImgRepository extends JpaRepository<ProfileImg, Long> {


   // ProfileImg findByProfileNo(Long profileNo);

    //유저별 이미지를 찾아야한다
    ProfileImg findByUserId(String userId);
    public void deleteByUserId(String userId);


//게시판으로 userId 프로필 이미지 가져오기
@Query(nativeQuery = true, value = "SELECT profile_img FROM user_profile WHERE user_id=?1")
byte[] findUserProfileImgByUserId(String userId);


}
