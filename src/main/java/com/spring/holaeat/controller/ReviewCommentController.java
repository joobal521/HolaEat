package com.spring.holaeat.controller;

import com.spring.holaeat.domain.review_comment.ReviewComment;
import com.spring.holaeat.domain.review_comment.ReviewCommentRepository;
import com.spring.holaeat.domain.review_comment.ReviewCommentRequestDto;
import com.spring.holaeat.payload.Response;
import com.spring.holaeat.service.ReviewCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//

@RequiredArgsConstructor
@RestController
public class ReviewCommentController {
    private final ReviewCommentRepository reviewCommentRepository;
    private final ReviewCommentService reviewCommentService;

    //댓글 등록
    @PostMapping(value = "/comment/{reviewNo}", consumes = "application/json")
    public ResponseEntity<ReviewComment> write(@PathVariable long reviewNo, WebRequest request, @RequestBody ReviewCommentRequestDto reviewCommentRequestDto) {
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);

        if (log != null) {
            reviewCommentRequestDto.setUserId(log);
            ReviewComment reviewComment = new ReviewComment(reviewCommentRequestDto);

            reviewCommentRepository.save(reviewComment);
            System.out.println("글작성 성공: " + log);
            System.out.println(reviewComment.getContent());

            return ResponseEntity.ok(reviewComment);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // 주어진 리뷰 번호에 해당하는 모든 댓글을 데이터베이스에서 검색하고 이를 클라이언트에게 반환
    @GetMapping(value = "/comment/{reviewNo}")
    public ResponseEntity<List<ReviewComment>> getComments(@PathVariable long reviewNo) {
        List<ReviewComment> comments = reviewCommentRepository.findAllByReviewNo(reviewNo);
        return ResponseEntity.ok(comments);
    }


    //수정
    @PutMapping(value = "/comment/{commentId}/update", consumes = {"multipart/form-data"})
    @Transactional
    public Response update(@PathVariable Long commentId, WebRequest request, @ModelAttribute ReviewCommentRequestDto reviewCommentRequestDto) {
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        if (log == null) {
            return new Response("update", "로그인 상태에서만 가능합니다.");
        }
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );

        if (!reviewComment.getUserId().equals(log)) {
            return new Response("update", "작성자만 수정할 수 있습니다.");
        }

        reviewCommentService.update(reviewComment, reviewCommentRequestDto);

        // 응답 객체에 content 값을 추가하여 클라이언트로 전송
        Response response = new Response("update", "success");

        return response;
    }

    //삭제
    @DeleteMapping("/comment/{commentId}/delete")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteComment(@PathVariable("commentId") long commentId) {
        Map<String, String> response = new HashMap<>();
        try {
            reviewCommentService.deleteByCommentId(commentId);
            response.put("message", "success");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
