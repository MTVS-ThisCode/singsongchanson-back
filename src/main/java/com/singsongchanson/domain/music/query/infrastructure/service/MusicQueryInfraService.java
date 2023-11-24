package com.singsongchanson.domain.music.query.infrastructure.service;

import com.singsongchanson.domain.room.command.application.dto.RoomResponseDTO;
import com.singsongchanson.domain.room.query.application.service.RoomQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MusicQueryInfraService {

    private final RoomQueryService roomQueryService;

    public RoomResponseDTO getRoomId(Long userNo) {
        RoomResponseDTO roomResponse = roomQueryService.findRoomByRoomOwnerVO(userNo);
        return roomResponse;
    }
}
