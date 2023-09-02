package com.spring.holaeat.controller;

import com.spring.holaeat.domain.admin.Admin;
import com.spring.holaeat.domain.admin.AdminRepository;
import com.spring.holaeat.domain.health.Health;
import com.spring.holaeat.domain.health.HealthFileVo;
import com.spring.holaeat.domain.health.HealthRepository;
import com.spring.holaeat.domain.health.HealthRequestDto;
import com.spring.holaeat.payload.Response;
import com.spring.holaeat.service.AdminService;
import com.spring.holaeat.service.HealthService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@SessionAttributes("authority")
@RequiredArgsConstructor
@RestController
public class HealthController {

    private final HealthService healthService;
    private final HealthRepository healthRepository;
    private final AdminService adminService;


    //다중 이미지 업로드
//    @PostMapping("health-write")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Long create(HealthFileVo healthFileVo) throws Exception {
    // 관리자 id
//        Admin admin= adminService.getAdminById(healthFileVo.getId());


//        HealthRequestDto healthDto =
//                HealthRequestDto.builder()
//                        .admin(admin)
//                        .title(healthFileVo.getTitle())
//                       .content(healthFileVo.getContent())
//                        .build();
    //       return healthService.create(healthDto, healthFileVo.getFile());
    //   }

    //관리자 글등록
    @PostMapping(value = "write-health", consumes = "multipart/form-data")
    public Map healthBoard(@ModelAttribute HealthRequestDto healthDto) throws Exception {
        JSONObject response = new JSONObject();

        try {
            Health health = new Health(healthDto);
            healthRepository.save(health);
            response.put("result", true);

        } catch (IllegalArgumentException e) {
            response.put("result", false);
        }
        return response.toMap();
    }

    //관리자 글 수정
    @PutMapping(value = "/update-health/{healthNo}", consumes = {"multipart/form-data"})
    public Response update(@PathVariable long healthNo, @ModelAttribute HealthRequestDto healthDto) {
        Health health = healthRepository.findById(healthNo).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (healthDto.getImg() == null) {

            System.out.println("기존 사진 넣기");
            byte[] img = health.getImg(); //원래 있는 이미지 빼놓기
            System.out.println("img" + img);
            healthService.updateHealth(health, healthDto);
            healthService.remainImage(health, img);

        } else {
            healthService.updateHealth(health, healthDto);
        }


        return new Response("update", "success");
    }


    //관리자 글 삭제
    @DeleteMapping("/delete-health/{healthNo}")
    public Response delete(@PathVariable("healthNo") long healthNo, @ModelAttribute HealthRequestDto healthDto) {


        Health health = healthRepository.findById(healthNo).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );


        healthService.healthBoardDelete(healthNo);
        System.out.println("게시글 삭제");


        return new Response("delete", "success");

    }


}
