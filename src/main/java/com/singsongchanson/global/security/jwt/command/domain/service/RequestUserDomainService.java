package com.singsongchanson.global.security.jwt.command.domain.service;

import com.singsongchanson.domain.user.command.application.dto.FindUserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface RequestUserDomainService {

    FindUserResponseDTO getUserByUserNo(Long userNo);
}
