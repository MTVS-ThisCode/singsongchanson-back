package com.singsongchanson.domain.comment.command.infrastructure.service;

import com.singsongchanson.domain.comment.command.domain.service.CommentDomainService;
import com.singsongchanson.domain.comment.query.service.CommentQueryService;
import com.singsongchanson.domain.user.command.application.dto.FindUserResponseDTO;
import com.singsongchanson.domain.user.query.application.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentInfraService implements CommentDomainService {

    private final UserQueryService userQueryService;

    @Override
    public FindUserResponseDTO getUserInfo(Long userNo) {

        FindUserResponseDTO findUserResponse = userQueryService.findUserByUserNo(userNo);

        return findUserResponse;
    }
}