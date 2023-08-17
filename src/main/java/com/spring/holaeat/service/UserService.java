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

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.List;
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

     //아이디 중복체크
     public boolean duplCheckUserId(String userId) {
          User user = userRepository.findByUserId(userId);
          return user != null;
     }

     //이메이 중복체크
     public  boolean duplCheckUserEmail(String userEamil) {
          Optional<User> user = userRepository.findByUserEmail(userEamil);
          return  user.isPresent();
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



     public void sendCodeToEmail(String toEmail) {
          this.checkDuplicatedEmail(toEmail);
          String title = "holaEat 이메일 인증 번호";
          String authCode = this.createCode();

          System.out.println("인증코드"+authCode);
          mailService.sendEmail(toEmail, title, authCode);
          // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )
          redisService.setValues(AUTH_CODE_PREFIX + toEmail,
                  authCode, Duration.ofMillis(this.authCodeExpirationMillis));
     }

     //이메일 중복확인
     private void checkDuplicatedEmail(String email) {
          Optional<User> user = userRepository.findByUserEmail(email);
          if (user.isPresent()) {
               log.debug("MemberServiceImpl.checkDuplicatedEmail exception occur email: {}", email);
               throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
          }
     }

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

     public void verifiedCode(String email, String authCode) {
          this.checkDuplicatedEmail(email);
          String redisAuthCode = redisService.getValues(AUTH_CODE_PREFIX + email);
          boolean authResult = redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(authCode);
          if (!authResult) {
               throw new BusinessLogicException(ExceptionCode.AUTH_CODE_IS_NOT_SAME);
          }
     }






}
