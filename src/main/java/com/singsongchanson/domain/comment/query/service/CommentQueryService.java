package com.singsongchanson.domain.comment.query.service;

import com.singsongchanson.domain.comment.command.application.dto.CommentResponseDTO;
import com.singsongchanson.domain.comment.command.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentQueryService {

    private final CommentRepository commentRepository;

    public List<CommentResponseDTO> findByRoomId(String roomId) {

        List<CommentResponseDTO> commentList = commentRepository.findByRoomId(roomId).stream()
                .map(CommentResponseDTO::from)
                .collect(Collectors.toList());

        return commentList;

    }
}
