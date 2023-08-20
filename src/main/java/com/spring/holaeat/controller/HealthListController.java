package com.spring.holaeat.controller;

import com.spring.holaeat.domain.health.Health;
import com.spring.holaeat.domain.health.HealthRepository;
import com.spring.holaeat.service.HealthService;
import com.spring.holaeat.util.ImageParsor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class HealthListController {


    private final HealthRepository healthRepository;
    private final HealthService healthService;


    //게시글 하나 조회
    @GetMapping("/health/{healthNo}")
    public String getHealthNo(Model model, @PathVariable long healthNo) {

        Health health = healthRepository.findByHealthNo(healthNo);
        model.addAttribute("health", health);

        if (health.getImg() == null)
            return "health";

        model.addAttribute("blob", ImageParsor.parseBlobToBase64(health.getImg()));
        return "health";

    }


    //게시글 전체 조회 페이징
//    @GetMapping("health-list/{pageNumber}")
//    public List<Health> getBoardAll(@PathVariable int pageNumber, @RequestParam(defaultValue = "") String keyword, @PageableDefault(size =3) Pageable pageable){
//        if(!keyword.equals((""))) {
//            String pattern = "%" + keyword + "%";
//            return healthRepository.findAllByTitleLike(pattern,pageable.withPage(pageNumber -1));
//        }else{
//            return healthRepository.findAll(pageable.withPage(pageNumber -1)).getContent();
//        }
//    }

    //전체 조회
    @GetMapping("/health-list")
    public String getHelthAll(Model model) {
        List<Health> list = healthService.findAllByOrderByHealthNoDesc(Pageable.unpaged());
        Map<Long, String> imageMap = new HashMap<>();

        for (Health health : list) {
            if (health.getImg() != null) {
                String base64Image = ImageParsor.parseBlobToBase64(health.getImg());
                imageMap.put(health.getHealthNo(), base64Image);
            }
        }

        model.addAttribute("healthList", list);
        model.addAttribute("imageMap", imageMap);
        System.out.println("이게 되는겨?");
        return "healthList";

    }
}
