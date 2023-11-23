package com.singsongchanson.domain.comment.query.controller;

import com.singsongchanson.domain.comment.command.application.dto.CommentResponseDTO;
import com.singsongchanson.domain.comment.query.service.CommentQueryService;
import com.singsongchanson.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentQueryController {

    private final CommentQueryService commentQueryService;
    @GetMapping("/room")
    public ApiResponse findCommentByRoomNo(@RequestParam String roomId) {

        List<CommentResponseDTO> commentList = commentQueryService.findByRoomId(roomId);

        return ApiResponse.success("성공적으로 조회되었습니다.", commentList);
    }
}