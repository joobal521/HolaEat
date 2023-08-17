package com.spring.holaeat.controller;

import com.spring.holaeat.domain.health.Health;
import com.spring.holaeat.domain.health.HealthRepository;
import com.spring.holaeat.service.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class healthListController {


    private final HealthRepository healthRepository;
    private final HealthService healthService;


    //게시글 하나 조회
//    @GetMapping("health/{no}")
//    public Health getHealthNo(Model model, @PathVariable long healthNo){
//
//        return boardRepository.findByNo(no);
//
//    }


    //게시글 전체 조회 페이징
    @GetMapping("list/{pageNumber}")
    public List<Health> getBoardAll(@PathVariable int pageNumber, @RequestParam(defaultValue = "") String keyword, @PageableDefault(size =3) Pageable pageable){
        if(!keyword.equals((""))) {
            String pattern = "%" + keyword + "%";
            return healthRepository.findAllByTitleLike(pattern,pageable.withPage(pageNumber -1));
        }else{
            return healthRepository.findAll(pageable.withPage(pageNumber -1)).getContent();
        }
    }

}
