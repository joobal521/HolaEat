package com.spring.holaeat.service;

import com.spring.holaeat.domain.health_board.HealthBoard;
import com.spring.holaeat.domain.health_board.HealthBoardRepository;
import com.spring.holaeat.domain.health_board.HealthBoardRequestDto;
import com.spring.holaeat.domain.health_img.HealthImg;
import com.spring.holaeat.domain.health_img.HealthImgRepository;
import com.spring.holaeat.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.awt.font.MultipleMaster;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HealthBoardService {

    private final HealthBoardRepository healthBoardRepository;
    private final HealthImgRepository healthImgRepository;
    private final FileHandler fileHandler;

    //전체 조회
    public List<HealthBoard> findAllByOrderByHealthNoDesc(){
        List<HealthBoard> list= healthBoardRepository.findAllByOrderByHealthNoDesc();
        return list;
    }

    public HealthBoard getHealthBoardByHealthNo(long healthNo){
        HealthBoard healthBoard=healthBoardRepository.findById(healthNo).orElseThrow(
                ()->new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        return healthBoard;

    }
    @Transactional
    public Long create(
            HealthBoardRequestDto healthBoardDto,
            List<MultipartFile> files
    ) throws Exception {
        // 파일 처리를 위한 Board 객체 생성
        HealthBoard healthBoard = new HealthBoard(healthBoardDto);

        List<HealthImg> healthImgList = fileHandler.parseFileInfo(files);

        // 파일이 존재할 때에만 처리
        if(!healthImgList.isEmpty()) {
            for(HealthImg healthImg : healthImgList) {
                // 파일을 DB에 저장
                healthBoard.addHealthImg(healthImgRepository.save(healthImg));
            }
        }

        return healthBoardRepository.save(healthBoard).getHealthNo();
    }



    //삭제
    @Transactional
    public void healthBoardDelete(long healthNo){
        //게시글 삭제
        healthBoardRepository.deleteById(healthNo);
    }











}
