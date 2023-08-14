package com.spring.holaeat.domain.health_board;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthBoardRepository extends JpaRepository<HealthBoard, Long> {

    public List<HealthBoard> findAllByTitleLike(String pattern, Pageable pageable);


    //Select * from boards where no=?
    HealthBoard findByHealthNo(long no);

    //내림차순
    public List<HealthBoard> findAllByOrderByHealthNoDesc();



}
