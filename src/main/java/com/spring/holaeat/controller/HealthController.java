package com.spring.holaeat.controller;

import com.spring.holaeat.domain.admin.Admin;
import com.spring.holaeat.domain.admin.AdminRepository;
import com.spring.holaeat.domain.health.Health;
import com.spring.holaeat.domain.health.HealthFileVo;
import com.spring.holaeat.domain.health.HealthRepository;
import com.spring.holaeat.domain.health.HealthRequestDto;
import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.domain.review.ReviewRequestDto;
import com.spring.holaeat.payload.Response;
import com.spring.holaeat.service.AdminService;
import com.spring.holaeat.service.HealthService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RequiredArgsConstructor
@RestController

@RequestMapping("api/v1/health")
public class HealthController {

    private final HealthService healthService;
    private final HealthRepository healthRepository;
    private final AdminRepository adminRepository;
    private final AdminService adminService;




   //관리자 글등록
    @PostMapping(value = "health-write", consumes = "multipart/form-data")
    public Map healthBoard(@ModelAttribute HealthRequestDto healthDto)throws Exception{
        JSONObject response =new JSONObject();

        try {
                Health health=new Health(healthDto);
                healthRepository.save(health);
                response.put("result", true);

        } catch (IllegalArgumentException e) {
            response.put("result", false);
        }
        return response.toMap();
    }

    //관리자 글 삭제
    @DeleteMapping("/{healthNo}/delete")
    public Response delete(@PathVariable("healthNo") long healthNo, @ModelAttribute HealthRequestDto healthDto) {


        Health health = healthRepository.findById(healthNo).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );


        healthService.healthBoardDelete(healthNo);
        System.out.println("게시글 삭제");


        return new Response("delete", "success");

    }


    //개별조회
//    @GetMapping("/health/{healthNo}")
//    public Map searchById(@PathVariable Long healthNo) {
//        healthService.getHealthByHealthNo(healthNo);
//
//        return
//    }



    //전체 조회
//    @GetMapping("health-board")
//    public List<BoardListResponseDto> searchAllDesc() {
//
//        // 게시글 전체 조회
//        List<Board> boardList = itemService.searchAllDesc();
//        // 반환할 List<BoardListResponseDto> 생성
//        List<BoardListResponseDto> responseDtoList = new ArrayList<>();
//
//        for(Board board : boardList){
//            // 전체 조회하여 획득한 각 게시글 객체를 이용하여 BoardListResponseDto 생성
//            BoardListResponseDto responseDto = new BoardListResponseDto(board);
//            responseDtoList.add(responseDto);
//        }
//
//        return responseDtoList;
//    }




}
