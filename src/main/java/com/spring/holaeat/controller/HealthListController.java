package com.spring.holaeat.controller;

import com.spring.holaeat.domain.health.Health;
import com.spring.holaeat.domain.health.HealthRepository;
import com.spring.holaeat.domain.review.Review;
import com.spring.holaeat.service.HealthService;
import com.spring.holaeat.util.ImageParsor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("health-list/{pageNumber}")
    public String getBoardAll(@PathVariable int pageNumber, @RequestParam(required = false, value = "keyword") String keyword, @RequestParam(required = false, value = "searchType") String searchType, @PageableDefault(size = 8) Pageable pageable, Model model){
        Map<Long, String> imageMap = new HashMap<>();
        List<Health> healthPage  = null;

        Pageable adjustedPageable = PageRequest.of(pageNumber - 1, pageable.getPageSize(), pageable.getSort());

        if (keyword != null && !keyword.isEmpty()) {
            String pattern = "%" + keyword + "%";
            if ("title".equals(searchType)) {
                healthPage = healthRepository.findAllByTitleLike(pattern, adjustedPageable);
            }

            if("all".equals(searchType)){
                healthPage = healthRepository.findAllFieldsByPattern(pattern, adjustedPageable);
            }
        } else {
            healthPage = healthRepository.findAllByOrderByHealthNoDesc(adjustedPageable);
        }

        model.addAttribute("healthList", healthPage); // healthPage를 모델에 추가

        for (Health health : healthPage) {
            if (health.getImg() != null) {
                String base64Image = ImageParsor.parseBlobToBase64(health.getImg());
                imageMap.put(health.getHealthNo(), base64Image);
            }
        }

        int totalLength = (int) healthRepository.count(); // 총 리뷰 수 가져오기
        int totalPages = (int) Math.ceil((double) totalLength / pageable.getPageSize()); // 전체 페이지 수 계산

        System.out.println("totalLength :" + totalLength );
        System.out.println("totalPages :" + totalPages );
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("healthList", healthPage);
        model.addAttribute("imageMapPage", imageMap);
        return "healthList";
    }

    //전체 조회
//    @GetMapping("/health-list")
//    public String getHelthAll(Model model) {
//        List<Health> list = healthService.findAllByOrderByHealthNoDesc(Pageable.unpaged());
//        Map<Long, String> imageMap = new HashMap<>();
//
//        for (Health health : list) {
//            if (health.getImg() != null) {
//                String base64Image = ImageParsor.parseBlobToBase64(health.getImg());
//                imageMap.put(health.getHealthNo(), base64Image);
//            }
//        }
//
//        model.addAttribute("healthList", list);
//        model.addAttribute("imageMap", imageMap);
//        System.out.println("이게 되는겨?");
//        return "healthList";
//
//    }

}
