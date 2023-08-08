package com.spring.holaeat.controller;


import com.spring.holaeat.domain.board.Board;
import com.spring.holaeat.domain.board.BoardRepository;
import com.spring.holaeat.domain.board.BoardRequestDto;
import com.spring.holaeat.payload.Response;
import com.spring.holaeat.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RequestMapping("api/v1/review")
@RestController
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    //게시글 작성

    @PostMapping(value = "write", consumes ={"multipart/form-data"} )
    public Response write(WebRequest request,@ModelAttribute BoardRequestDto boardDto){
        String log = (String)request.getAttribute("log" , WebRequest.SCOPE_SESSION);
        System.out.println("log 확인" + log);

        if(log ==null)
            return new Response("post","fail");

        boardDto.setUserId(log);
        Board board = new Board(boardDto);

        boardRepository.save(board);
        return new Response("post","success");
    }



    @GetMapping("list")
    public List<Board> getBoardAll(){
        List<Board> list = boardService.getBoardAll();
        return list;
    }

//수정

    @PutMapping(value = "{reviewNo}/update", consumes = {"multipart/form-data"})
    public Response update(@PathVariable long reviewNo, WebRequest request, @ModelAttribute BoardRequestDto updateBoardDto) {
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        if (log == null) {
            return new Response("update", "로그인 상태에서만 가능합니다.");
        }
        Board board = boardRepository.findById(reviewNo).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")

        );

        System.out.println(log + " = log 확인용");

        if(!board.getUserId().equals(log)){
            return new Response("update","작성자만 수정할 수 있습니다.");
        }

        boardService.update(board,updateBoardDto);

        System.out.println("수정 성공" + " log : " + log);
        return new Response("update", "success");
    }

//삭제

    @DeleteMapping("{reviewNo}/delete")
    public Response delete(@PathVariable long reviewNo, WebRequest request, @ModelAttribute BoardRequestDto deleteBoardDto){
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);

        if (log == null) {
            return new Response("delete", "로그인 상태에서만 가능합니다.");
        }

        Board board = boardRepository.findById(reviewNo).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );


        if(!board.getUserId().equals(log)){
            return  new Response("delete","작성자만 삭제할 수 있습니다.");
        }

        boardService.delete(reviewNo);
        System.out.println("게시글 삭제");

        return new Response("delete", "success");

    }

//
//    //게시글 하나 조회
//    @GetMapping("/{reviewNo}")
//    public List<Board> getBoardByNo(@PathVariable long reviewNo){
//        return boardRepository.findByNo(reviewNo);
//    }
//
//    //게시글 목록 조회
//    @GetMapping("list/{pageNumber}")
//    public List<Board> getBoardAll(@PathVariable int pageNumber,
//                                   @RequestParam(required = false) String keyword,
//                                   @PageableDefault(size = 10) Pageable pageable) {
//
//        if (keyword != null && !keyword.isEmpty()) {
//            String pattern = "%" + keyword + "%";
//            return boardRepository.findAllByTitleLike(pattern, pageable.withPage(pageNumber - 1));
//        } else {
//            return boardRepository.findAll(pageable.withPage(pageNumber - 1)).getContent();
//        }
//    }





    @GetMapping("/review/list")
    public String  boardlist(Model model){ // 데이터를 담아 페이지로 보내기 위해 Model 자료형을 인자로
        model.addAttribute("list", boardService.boardlist()); // boardService에서 생성한 boardlist메소드를 통해 list가 반환되는데 해당 list를 "list"라는 이름으로 넘겨주겠다는 것(html에 나올 수 있게)
        return "boardlist";
    }




}


