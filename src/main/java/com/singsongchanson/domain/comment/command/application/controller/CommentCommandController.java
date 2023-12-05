package com.singsongchanson.domain.comment.command.application.controller;

import com.singsongchanson.domain.comment.command.application.dto.CommentResponseDTO;
import com.singsongchanson.domain.comment.command.application.dto.CreateCommentRequestDTO;
import com.singsongchanson.domain.comment.command.application.dto.UpdateCommentRequestDTO;
import com.singsongchanson.domain.comment.command.application.service.CommentCommandService;
import com.singsongchanson.global.common.response.ApiResponse;
import com.singsongchanson.global.security.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@Tag(name = "CommentCommandController", description = "댓글 API Document")
public class CommentCommandController {

    private final CommentCommandService commentCommandService;
    @Operation(summary = "댓글 저장 요청", description = "댓글을 저장합니다.", tags = { "CommentCommandController" })
    @PostMapping
    public ApiResponse createComment(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                     @RequestBody CreateCommentRequestDTO createCommentRequest) {

        CommentResponseDTO commentResponse = commentCommandService.saveComment(userPrincipal, createCommentRequest);

        return ApiResponse.success("성공적으로 저장되었습니다.", commentResponse);
    }

    @Operation(summary = "댓글 수정 요청", description = "댓글을 수정합니다.", tags = { "CommentCommandController" })
    @PutMapping("/{commentNo}")
    public ApiResponse updateComment(@PathVariable Long commentNo,
                                     @AuthenticationPrincipal UserPrincipal userPrincipal,
                                     @RequestBody UpdateCommentRequestDTO updateCommentRequest) {

        CommentResponseDTO commentResponse = commentCommandService.updateComment(commentNo, userPrincipal, updateCommentRequest);

        return ApiResponse.success("성공적으로 수정되었습니다.", commentResponse);
    }

    @Operation(summary = "댓글 삭제 요청", description = "댓글을 삭제합니다.", tags = { "CommentCommandController" })
    @DeleteMapping("/{commentNo}")
    public ApiResponse deleteComment(@PathVariable Long commentNo,
                                     @AuthenticationPrincipal UserPrincipal userPrincipal) {

        boolean result = commentCommandService.deleteComment(commentNo, userPrincipal);

        return ApiResponse.success("성공적으로 삭제되었습니다.", result);
    }
}
