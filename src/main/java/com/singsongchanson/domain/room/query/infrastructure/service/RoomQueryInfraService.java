package com.singsongchanson.domain.room.query.infrastructure.service;

import com.singsongchanson.domain.room.query.domain.service.RoomQueryDomainService;
import com.singsongchanson.domain.user.command.application.dto.FindUserResponseDTO;
import com.singsongchanson.domain.user.query.application.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomQueryInfraService implements RoomQueryDomainService {

    private final UserQueryService userQueryService;
    @Override
    public FindUserResponseDTO getUserInfo(Long userNo) {

        FindUserResponseDTO findUserResponse = userQueryService.findUserByUserNo(userNo);

        return findUserResponse;
    }
}
