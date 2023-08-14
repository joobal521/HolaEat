package com.spring.holaeat.controller;

import com.spring.holaeat.domain.health_board.HealthBoard;
import com.spring.holaeat.domain.health_board.HealthBoardFileVo;
import com.spring.holaeat.domain.health_board.HealthBoardRepository;
import com.spring.holaeat.domain.health_board.HealthBoardRequestDto;
import com.spring.holaeat.service.HealthBoardService;
import com.spring.holaeat.service.HealthImgService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController

@RequestMapping("api/v1/health")
public class HealthBoardController {

    private final HealthBoardService healthBoardService;
    private final HealthBoardRepository healthBoardRepository;


    //게시글 작성(관리지만)
//
//    @PostMapping("health-board")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Long creat(HealthBoardFileVo healthBoardFileVo) throws Exception{
//        //admin id로 조회하는 메소드 필요
//        //HealthBoardRequestDto healthBaordDto=
//   // }


    @PostMapping("write")
    @ResponseStatus(HttpStatus.CREATED)
    public Map boardCreate(@RequestBody HealthBoardRequestDto healthBoardDto, HealthBoardFileVo healthBoardFileVo)throws Exception{
        JSONObject response =new JSONObject();


        try {
            //admin으로 조회하는 메소드
            //Admin admin=
            Long.parseLong((healthBoardFileVo.getId()));
            healthBoardService.create(healthBoardDto,healthBoardFileVo.getFile());
            response.put("result",true);

        }catch (IllegalArgumentException e){
            response.put("result", false);
        }
        return response.toMap();
    }

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
