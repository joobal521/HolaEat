package com.spring.holaeat.domain.health;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HealthRepository extends JpaRepository<Health, Long> {

     public List<Health> findAllByTitleLike(String pattern, Pageable pageable);

    @Query("SELECT h FROM Health h WHERE " +
            "LOWER(h.title) LIKE LOWER(:pattern)")
    List <Health> findAllFieldsByPattern(@Param("pattern") String pattern, Pageable pageable);
    //Select * from boards where no=?
    Health findByHealthNo(long no);

    //내림차순
   public List<Health> findAllByOrderByHealthNoDesc(Pageable adjustedPageable);

   public List<Health> findAll();



}
