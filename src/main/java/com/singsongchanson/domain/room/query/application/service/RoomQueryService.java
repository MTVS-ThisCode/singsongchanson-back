package com.singsongchanson.domain.room.query.application.service;

import com.singsongchanson.domain.room.command.application.dto.RoomDataResponseDTO;
import com.singsongchanson.domain.room.command.application.dto.RoomResponseDTO;
import com.singsongchanson.domain.room.command.domain.aggregate.entity.Room;
import com.singsongchanson.domain.room.command.domain.repository.RoomDataRepository;
import com.singsongchanson.domain.room.command.domain.repository.RoomRepository;
import com.singsongchanson.domain.room.query.domain.service.RoomQueryDomainService;
import com.singsongchanson.domain.user.command.application.dto.FindUserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomQueryService {

    private final RoomRepository roomRepository;
    private final RoomDataRepository roomDataRepository;
    private final RoomQueryDomainService roomQueryDomainService;

    public List<RoomResponseDTO> findAllRooms() {

        List<Room> roomList = roomRepository.findAll();

        List<RoomResponseDTO> roomResponseList = new ArrayList<>();

        for(Room room : roomList) {
           Long userNo = room.getRoomOwnerVO().getUserNo();
           FindUserResponseDTO findUserResponse = roomQueryDomainService.getUserInfo(userNo);

           RoomResponseDTO roomResponseDTO = new RoomResponseDTO(
                   room.getRoomId(),
                   userNo,
                   findUserResponse.getNickName(),
                   findUserResponse.getProfileImg()
           );
           roomResponseList.add(roomResponseDTO);
        }
        return roomResponseList;
    }

    public RoomDataResponseDTO findRoomDataById(String roomId) {

        RoomDataResponseDTO roomDataResponse = roomDataRepository.findById(roomId)
                .map(RoomDataResponseDTO::from)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 데이터가 없습니다."));

        return roomDataResponse;
    }
}
