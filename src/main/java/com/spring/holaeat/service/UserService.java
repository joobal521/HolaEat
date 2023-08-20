package com.spring.holaeat.service;

import com.spring.holaeat.domain.user.User;
import com.spring.holaeat.domain.user.UserRequestDto;
import com.spring.holaeat.domain.user.UserRepository;
import com.spring.holaeat.exception.BusinessLogicException;
import com.spring.holaeat.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
     private static final String AUTH_CODE_PREFIX = "AuthCode ";
     private  final UserRepository userRepository;
     private final MailService mailService;
     private final RedisService redisService;

     public List<User>findByIdAndEmail(String userId, String userPassword){
          return (List<User>) userRepository.findAllByUserIdAndUserEmail(userId,userPassword);
     }

     public List<User> getUserAll(){
          List<User> list= userRepository.findAll();
          return list;
     }

     public User getUserById(String userId){
          User user=userRepository.findById(userId).orElseThrow(
                  ()->new IllegalArgumentException("존재하지 않는 사용자입니다.")
          );
          return user;

     }
     //이메일로 조회
     public User getUserByEmail(String userEmail){
          User user=userRepository.findByUserEmail(userEmail).orElseThrow(
                  ()->new IllegalArgumentException("존재하지 않는 이메일입니다.")
          );
          return user;
     }

     //이메일과 이름으로 아이디 찾기
     public User findUserByEmailAndName(String userEmail, String userId) {
          return userRepository.findByUserEmailAndUserName(userEmail, userId);
     }

     //아이디 중복체크
     public boolean duplCheckUserId(String userId) {
          User user = userRepository.findByUserId(userId);
          return user != null;
     }

     //이메일 중복체크
     public  boolean duplCheckUserEmail(String userEamil) {
          Optional<User> user = userRepository.findByUserEmail(userEamil);
          return  user.isPresent();
     }

     //비밀번호 찾기(비밀번호 바꾸기)
     @Transactional
     public void updateNewPwd(String userEmail, UserRequestDto userDto) {
          User user=getUserByEmail(userEmail);
          if (user != null) {
               user.updatePwd(userDto);
          } else {
               throw new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
          }

     }


     //비밀번호 조회
     public User findByUserPassword(String userPassword) {
          System.out.println(userRepository.findByUserPassword(userPassword));
          return userRepository.findByUserPassword(userPassword);

     }

     //비밀번호 보내기
     public String sendPassword(String toEmail, String userEmail) {
          String title = "holaEat에서 비밀번호 보내드려요";
          User user=this.getUserByEmail(userEmail);
          String password= String.valueOf(this.findByUserPassword(user.getUserPassword()));
          System.out.println("비밀번호"+password);
          mailService.sendEmail(toEmail, title,password);

          return password;
     }



     //회원가입
     public User createUser(UserRequestDto userDto){
          User user=new User(userDto);
          userRepository.save(user);

          return user;

     }


     //회원탈퇴
     @Transactional
     public void deleteUserById(String userId){
          userRepository.deleteById(userId);
     }

     //회원정보 수정
     @Transactional
     public void updateUser(String userId, UserRequestDto userDto){
          User user=getUserById(userId);
          if (user != null) {
               user.update(userDto);
          } else {
               throw new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
          }

     }

     @Value("${spring.mail.auth-code-expiration-millis}")
     private long authCodeExpirationMillis;
     private String authCode;

     //인증코드 만드는 난수
     private String createCode() {
          int lenth = 6;
          try {
               Random random = SecureRandom.getInstanceStrong();
               StringBuilder builder = new StringBuilder();
               for (int i = 0; i < lenth; i++) {
                    builder.append(random.nextInt(10));
               }
               return builder.toString();
          } catch (NoSuchAlgorithmException e) {
               log.debug("MemberService.createCode() exception occur");
               throw new BusinessLogicException(ExceptionCode.NO_SUCH_ALGORITHM);
          }
     }


     //이메일 보내기
     public String sendCodeToEmail(String toEmail) {
          String title = "holaEat 이메일 인증 번호";
          authCode = this.createCode();
          System.out.println("인증코드"+authCode);
          mailService.sendEmail(toEmail, title, authCode);

          return authCode;
     }



     //이메일 중복확인
//     private void checkDuplicatedEmail(String email) {
//          Optional<User> user = userRepository.findByUserEmail(email);
//          if (user.isPresent()) {
//               log.debug("MemberServiceImpl.checkDuplicatedEmail exception occur email: {}", email);
//               throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
//          }
//     }



//    public void verifiedCode(String email, String authCode) {
//          this.checkDuplicatedEmail(email);
//          String redisAuthCode = redisService.getValues(AUTH_CODE_PREFIX + email);
//          boolean authResult = redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(authCode);
//          if (!authResult) {
//               throw new BusinessLogicException(ExceptionCode.AUTH_CODE_IS_NOT_SAME);
//          }
//     }






}
