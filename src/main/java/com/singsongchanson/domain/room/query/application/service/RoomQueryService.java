package com.singsongchanson.domain.room.query.application.service;

import com.singsongchanson.domain.room.command.application.dto.RoomDataResponseDTO;
import com.singsongchanson.domain.room.command.application.dto.RoomResponseDTO;
import com.singsongchanson.domain.room.command.domain.repository.RoomDataRepository;
import com.singsongchanson.domain.room.command.domain.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomQueryService {

    private final RoomRepository roomRepository;
    private final RoomDataRepository roomDataRepository;

    public List<RoomResponseDTO> findAllRooms() {

        List<RoomResponseDTO> roomList = roomRepository.findAll().stream()
                .map(RoomResponseDTO::from)
                .collect(Collectors.toList());

        return roomList;
    }

    public RoomDataResponseDTO findRoomDataById(String roomId) {

        RoomDataResponseDTO roomDataResponse = roomDataRepository.findById(roomId)
                .map(RoomDataResponseDTO::from)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 데이터가 없습니다."));

        return roomDataResponse;
    }

    public RoomResponseDTO findRoomByRoomId(String roomId) {

        RoomResponseDTO roomResponse = roomRepository.findById(roomId)
                .map(RoomResponseDTO::from)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 데이터가 없습니다."));

        return roomResponse;
    }
}
