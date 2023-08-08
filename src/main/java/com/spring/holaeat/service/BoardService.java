package com.spring.holaeat.service;

import com.spring.holaeat.domain.board.Board;
import com.spring.holaeat.domain.board.BoardRepository;
import com.spring.holaeat.domain.board.BoardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    //전체 적용?

    public List<Board> getBoardAll(){
        List<Board> list = boardRepository.findAll();
        System.out.println("list : " + list);
        return list;
    }

    //조회
    public List<Board> boardlist(){
        return boardRepository.findAll();}



    //수정
    @Transactional
    public void update(Board board, BoardRequestDto boardDto){

        board.update(boardDto);
        boardRepository.save(board);
    }


        //삭제
    @Transactional
public void delete(long reviewNo){
        boardRepository.deleteById(reviewNo);
}










}
