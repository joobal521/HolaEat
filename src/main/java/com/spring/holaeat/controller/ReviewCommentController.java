package com.spring.holaeat.controller;

import com.spring.holaeat.domain.review_comment.ReviewComment;
import com.spring.holaeat.domain.review_comment.ReviewCommentRepository;
import com.spring.holaeat.domain.review_comment.ReviewCommentRequestDto;
import com.spring.holaeat.payload.Response;
import com.spring.holaeat.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
//

@RequiredArgsConstructor
@RestController
public class ReviewCommentController {
    private final ReviewCommentRepository reviewCommentRepository;
    private final CommentService commentService;

    @PostMapping(value = "/comment", consumes = "multipart/form-data")
    public Map<String, Object> write(WebRequest request, @ModelAttribute ReviewCommentRequestDto reviewCommentRequestDto) {
        JSONObject response = new JSONObject();
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        System.out.println("log 확인" + log);

        try {
            if (log != null) {
                reviewCommentRequestDto.setUserId(log);
                ReviewComment reviewComment = new ReviewComment(reviewCommentRequestDto);


                reviewCommentRepository.save(reviewComment);
                response.put("reviewCommentlist", true);

            } else {
                response.put("reviewCommentlist", false);
            }

        } catch (IllegalArgumentException e) {
            response.put("reviewCommentlist", false);

        }

        return response.toMap();

    }


//수정

    @PutMapping(value = "/{commentId}/update", consumes = {"multipart/form-data"})
    public Response update(@PathVariable String commentId, WebRequest request, @ModelAttribute ReviewCommentRequestDto reviewCommentRequestDto, Model model) {
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        if (log == null) {
            return new Response("update", "로그인 상태에서만 가능합니다.");
        }
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")

        );

        System.out.println(log + " = log 확인용");

        if (!reviewComment.getUserId().equals(log)) {
            System.out.println(log + " = log 확인용");
            return new Response("update", "작성자만 수정할 수 있습니다.");
        }

        commentService.update(reviewComment, reviewCommentRequestDto);

        System.out.println("수정 성공" + " log : " + log);
        return new Response("update", "success");
    }


//삭제

    @DeleteMapping("/{commentId}/delete")
    public Response delete(@PathVariable("commentId") String commentId, WebRequest request, @ModelAttribute ReviewCommentRequestDto reviewCommentRequestDto) {
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);

        if (log == null) {
            return new Response("delete", "로그인 상태에서만 가능합니다.");
        }

        ReviewComment reviewComment = reviewCommentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );


        if (!reviewComment.getUserId().equals(log)) {
            return new Response("delete", "작성자만 삭제할 수 있습니다.");
        }

        commentService.delete(commentId);
        System.out.println("게시글 삭제");


        return new Response("delete", "success");

    }




}
