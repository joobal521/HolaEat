package com.spring.holaeat.controller;

import com.spring.holaeat.domain.health.Health;
import com.spring.holaeat.service.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class healthListController {


    private final HealthService healthService;


    //게시글 하나 조회
//    @GetMapping("health/{no}")
//    public Health getHealthNo(Model model, @PathVariable long healthNo){
//
//        return boardRepository.findByNo(no);
//
//    }

}
