package com.singsongchanson.domain.comment.command.application.controller;

import com.singsongchanson.domain.comment.command.application.dto.CommentResponseDTO;
import com.singsongchanson.domain.comment.command.application.dto.CreateCommentRequestDTO;
import com.singsongchanson.domain.comment.command.application.dto.UpdateCommentRequestDTO;
import com.singsongchanson.domain.comment.command.application.service.CommentCommandService;
import com.singsongchanson.global.common.response.ApiResponse;
import com.singsongchanson.global.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentCommandController {

    private final CommentCommandService commentCommandService;
    @PostMapping
    public ApiResponse createComment(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                     @RequestBody CreateCommentRequestDTO createCommentRequest) {

        CommentResponseDTO commentResponse = commentCommandService.saveComment(userPrincipal, createCommentRequest);

        return ApiResponse.success("성공적으로 저장되었습니다.", commentResponse);
    }

    @PutMapping("/{commentNo}")
    public ApiResponse updateComment(@PathVariable Long commentNo,
                                     @AuthenticationPrincipal UserPrincipal userPrincipal,
                                     @RequestBody UpdateCommentRequestDTO updateCommentRequest) {

        CommentResponseDTO commentResponse = commentCommandService.updateComment(commentNo, userPrincipal, updateCommentRequest);

        return ApiResponse.success("성공적으로 수정되었습니다.", commentResponse);
    }

    @DeleteMapping("/{commentNo}")
    public ApiResponse deleteComment(@PathVariable Long commentNo,
                                     @AuthenticationPrincipal UserPrincipal userPrincipal) {

        boolean result = commentCommandService.deleteComment(commentNo, userPrincipal);

        return ApiResponse.success("성공적으로 삭제되었습니다.", result);
    }
}
