//package com.spring.holaeat.controller;
//
//import com.spring.holaeat.domain.review_comment.ReviewComment;
//import com.spring.holaeat.domain.review_comment.ReviewCommentRepository;
//import com.spring.holaeat.domain.review_comment.ReviewCommentRequestDto;
//import lombok.RequiredArgsConstructor;
//import org.json.JSONObject;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.context.request.WebRequest;
//
//import java.util.Map;
////
//
//@RequiredArgsConstructor
//@RestController
//public class ReviewCommentController {
//    private final ReviewCommentRepository reviewCommentRepository;
//
//    @PostMapping(value = "/comment", consumes = "multipart/form-data")
//    public Map<String, Object> write(WebRequest request, @ModelAttribute ReviewCommentRequestDto reviewCommentRequestDto) {
//        JSONObject response = new JSONObject();
//        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
//        System.out.println("log 확인" + log);
//
//        try {
//            if (log != null) {
//                reviewCommentRequestDto.setUserId(log);
//                ReviewComment reviewComment = new ReviewComment(reviewCommentRequestDto);
//
//
//                reviewCommentRepository.save(reviewComment);
//                response.put("reviewCommentlist", true);
//
//            } else {
//                response.put("reviewCommentlist", false);
//            }
//
//        } catch (IllegalArgumentException e) {
//            response.put("reviewCommentlist", false);
//
//        }
//
//        return response.toMap();
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////    @Autowired
////    public ReviewCommentController(ReviewCommentRepository reviewCommentRepository) {
////        this.reviewCommentRepository = reviewCommentRepository;
////    }
//
//    // 댓글 등록
////    @PostMapping(value = "/{commentId}")
////    public ReviewComment createComment(@RequestBody ReviewCommentRequestDto commentRequest) {
////        ReviewComment newComment = new ReviewComment(commentRequest);
////        return reviewCommentRepository.save(newComment);
////    }
//
//    // 댓글 조회
////    @GetMapping("/{commentId}")
////    public ReviewComment getComment(@PathVariable String commentId) {
////        return reviewCommentRepository.findById(commentId)
////                .orElseThrow(() -> new ChangeSetPersister.NotFoundException("댓글을 찾을 수 없습니다."));
////    }
//
//    // 댓글 삭제
//    @DeleteMapping("/{commentId}")
//    public void deleteComment(@PathVariable String commentId) {
//        reviewCommentRepository.deleteById(commentId);
//    }
////    @PostMapping(value = "")
////    public Map<String, Object> commentWrite(WebRequest request, @ModelAttribute ReviewCommentRequestDto reviewCommentRequestDto){
////        JSONObject response = new JSONObject();
////        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
////        System.out.println("log 확인" + log);
////
////        try {
////            if (log != null) {
////                reviewCommentRequestDto.setUserId(log);
////                ReviewComment reviewComment = new ReviewComment(reviewCommentRequestDto);
////
////
////                reviewCommentRepository.save(reviewComment);
////                response.put("reviewlist", true);
////
////            } else {
////                response.put("reviewlist", false);
////            }
////
////        } catch (IllegalArgumentException e) {
////            response.put("reviewlist", false);
////
////        }
////
////        return response.toMap();
//
//
//
////    }
//
//
//
//
//
//
//}
