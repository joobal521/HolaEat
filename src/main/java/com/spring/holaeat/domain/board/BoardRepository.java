package com.spring.holaeat.domain.board;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

//    public List<Board> findByNo(long reviewNo);
//
//    public List<Board> findAllByTitleLike(String pattern, Pageable pageable);
}
