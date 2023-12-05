package com.singsongchanson.domain.comment.query.controller;

import com.singsongchanson.domain.comment.command.application.dto.CommentResponseDTO;
import com.singsongchanson.domain.comment.query.service.CommentQueryService;
import com.singsongchanson.global.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
@Tag(name = "CommentQueryController", description = "댓글 API Document")
public class CommentQueryController {

    private final CommentQueryService commentQueryService;

    @Operation(summary = "댓글 조회 요청", description = "싱송룸에 작성된 댓글을 조회합니다.", tags = { "CommentQueryController" })
    @GetMapping("/room")
    public ApiResponse findCommentByRoomNo(@RequestParam String roomId) {

        List<CommentResponseDTO> commentList = commentQueryService.findByRoomId(roomId);

        return ApiResponse.success("성공적으로 조회되었습니다.", commentList);
    }
}