package com.singsongchanson.domain.room.query.domain.service;

import com.singsongchanson.domain.comment.command.application.dto.CommentResponseDTO;
import com.singsongchanson.domain.user.command.application.dto.FindUserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public interface RoomQueryDomainService {
    FindUserResponseDTO getUserInfo(Long userNo);

    List<CommentResponseDTO> getRoomComment(String roomId);
}
