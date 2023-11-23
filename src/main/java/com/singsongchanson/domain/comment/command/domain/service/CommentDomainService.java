package com.singsongchanson.domain.comment.command.domain.service;

import com.singsongchanson.domain.user.command.application.dto.FindUserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface CommentDomainService {

    FindUserResponseDTO getUserInfo(Long userNo);
}