package com.singsongchanson.domain.room.query.infrastructure.service;

import com.singsongchanson.domain.comment.command.application.dto.CommentResponseDTO;
import com.singsongchanson.domain.comment.query.service.CommentQueryService;
import com.singsongchanson.domain.room.query.domain.service.RoomQueryDomainService;
import com.singsongchanson.domain.user.command.application.dto.FindUserResponseDTO;
import com.singsongchanson.domain.user.query.application.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomQueryInfraService implements RoomQueryDomainService {

    private final UserQueryService userQueryService;
    private final CommentQueryService commentQueryService;

    @Override
    public FindUserResponseDTO getUserInfo(Long userNo) {

        FindUserResponseDTO findUserResponse = userQueryService.findUserByUserNo(userNo);

        return findUserResponse;
    }

    @Override
    public List<CommentResponseDTO> getRoomComment(String roomId) {

        List<CommentResponseDTO> commentResponse = commentQueryService.findByRoomId(roomId);

        return commentResponse;
    }
}
