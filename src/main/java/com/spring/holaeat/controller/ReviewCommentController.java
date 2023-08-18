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
import java.util.List;
//

@RequiredArgsConstructor
@RestController
public class ReviewCommentController {
    private final ReviewCommentRepository reviewCommentRepository;
    private final ReviewCommentService reviewCommentService;

//    @PostMapping(value = "/comment?{reviewNo}", consumes = "multipart/form-data")
//    public String write(@PathVariable long reviewNo, WebRequest request, @ModelAttribute ReviewCommentRequestDto reviewCommentRequestDto) {
//
//        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
//        System.out.println("log 확인" + log);
//
//            if (log != null) {
//                reviewCommentRequestDto.setUserId(log);
//                ReviewComment reviewComment = new ReviewComment(reviewCommentRequestDto);
//                reviewCommentRepository.save(reviewComment);
//                System.out.println("글작성 성공:"+log);
//                System.out.println(reviewComment.getContent());
//
//            }
//
//
//        return "review/"+reviewNo;
//
//    }

    @PostMapping(value = "/comment/{reviewNo}", consumes = "application/json")
    public ResponseEntity<ReviewComment> write(@PathVariable long reviewNo, WebRequest request, @RequestBody ReviewCommentRequestDto reviewCommentRequestDto) {
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);

        if (log != null) {
            reviewCommentRequestDto.setUserId(log);
            ReviewComment reviewComment = new ReviewComment(reviewCommentRequestDto);

            reviewCommentRepository.save(reviewComment);
            System.out.println("글작성 성공: " + log);
            System.out.println(reviewComment.getContent());

            return ResponseEntity.ok(reviewComment); // Return the new comment
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Return unauthorized if not logged in
    }


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

    return new Response("update", "success");
}


//    @PutMapping(value = "/{commentId}/update", consumes = {"multipart/form-data"})
//    @Transactional
//    public Response update(@PathVariable String commentId, WebRequest request, @ModelAttribute ReviewCommentRequestDto reviewCommentRequestDto) {
//        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
//        if (log == null) {
//            return new Response("update", "로그인 상태에서만 가능합니다.");
//        }
//        ReviewComment reviewComment = reviewCommentRepository.findById(Long.valueOf(commentId)).orElseThrow(
//                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
//
//        );
//
//        System.out.println(log + " = log 확인용");
//
//        if (!reviewComment.getUserId().equals(log)) {
//            System.out.println(log + " = log 확인용");
//            return new Response("update", "작성자만 수정할 수 있습니다.");
//        }
//
//        reviewCommentService.update(reviewComment, reviewCommentRequestDto);
//
//        System.out.println("수정 성공" + " log : " + log);
//        return new Response("update", "success");
//    }

//삭제
    @DeleteMapping("/comment/{commentId}/delete")
    @Transactional
    public ResponseEntity<Response> deleteComment(@PathVariable("commentId") long commentId, WebRequest request) {
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        System.out.println("컨트롤러 로그인확인전 commentID" +commentId );

        if (log != null) {
            // 로그인한 사용자와 댓글 작성자가 동일한 경우에만 삭제 가능하도록 처리
            ReviewComment comment = reviewCommentRepository.findById(commentId)
                    .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
            if (comment.getUserId().equals(log)) {
                System.out.println("컨트롤러 로그인시 commentID" +commentId );
                reviewCommentService.delete(commentId);
                return ResponseEntity.ok(new Response("delete", "success"));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Response("delete", "작성자만 삭제할 수 있습니다."));
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}
