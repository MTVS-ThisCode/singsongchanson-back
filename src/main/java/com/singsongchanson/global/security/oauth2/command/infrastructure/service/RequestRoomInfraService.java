package com.singsongchanson.global.security.oauth2.command.infrastructure.service;

import com.singsongchanson.domain.room.command.application.service.RoomCommandService;
import com.singsongchanson.global.security.oauth2.command.domain.service.RequestRoomDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestRoomInfraService implements RequestRoomDomainService {

    private final RoomCommandService roomCommandService;

    @Override
    public void createRoomService(Long userNo) {
        roomCommandService.createRoom(userNo);
    }
}
