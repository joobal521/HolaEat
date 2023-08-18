package com.spring.holaeat.domain.health;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthRepository extends JpaRepository<Health, Long> {

     public List<Health> findAllByTitleLike(String pattern, Pageable pageable);


    //Select * from boards where no=?
    Health findByHealthNo(long no);

    //내림차순
   public List<Health> findAllByOrderByHealthNoDesc(Pageable adjustedPageable);



}
