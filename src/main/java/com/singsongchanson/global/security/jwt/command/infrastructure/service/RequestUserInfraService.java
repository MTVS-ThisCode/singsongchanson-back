package com.singsongchanson.global.security.jwt.command.infrastructure.service;

import com.singsongchanson.domain.user.command.application.dto.FindUserResponseDTO;
import com.singsongchanson.domain.user.query.application.service.UserQueryService;
import com.singsongchanson.global.security.jwt.command.domain.service.RequestUserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestUserInfraService implements RequestUserDomainService {

    private final UserQueryService userQueryService;

    @Override
    public FindUserResponseDTO getUserByUserNo(Long userNo) {
        return userQueryService.findUserByUserNo(userNo);
    }

}
