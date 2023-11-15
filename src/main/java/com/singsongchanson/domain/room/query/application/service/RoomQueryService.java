package com.singsongchanson.domain.room.query.application.service;

import com.singsongchanson.domain.room.command.application.dto.FindRoomDataResponseDTO;
import com.singsongchanson.domain.room.command.application.dto.RoomDataResponseDTO;
import com.singsongchanson.domain.room.command.application.dto.RoomResponseDTO;
import com.singsongchanson.domain.room.command.domain.aggregate.entity.Room;
import com.singsongchanson.domain.room.command.domain.aggregate.entity.RoomData;
import com.singsongchanson.domain.room.command.domain.repository.RoomDataRepository;
import com.singsongchanson.domain.room.command.domain.repository.RoomRepository;
import com.singsongchanson.domain.room.query.domain.service.RoomQueryDomainService;
import com.singsongchanson.domain.user.command.application.dto.FindUserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomQueryService {

    private final RoomRepository roomRepository;
    private final RoomDataRepository roomDataRepository;
    private final RoomQueryDomainService roomQueryDomainService;

    public List<RoomResponseDTO> findAllRooms() {

        List<Room> roomList = roomRepository.findAll();

        List<RoomResponseDTO> roomResponseList = new ArrayList<>();

        for (Room room : roomList) {
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

    public FindRoomDataResponseDTO findRoomDataById(String roomId) {

        Optional<Room> room = roomRepository.findById(roomId);
        Optional<RoomData> roomData = roomDataRepository.findById(roomId);

        if (room.isPresent() && roomData.isPresent()) {
            Long userNo = room.get().getRoomOwnerVO().getUserNo();
            RoomData findedRoomData = roomData.get();
            FindUserResponseDTO findUserResponse = roomQueryDomainService.getUserInfo(userNo);

            FindRoomDataResponseDTO findRoomDataResponse = new FindRoomDataResponseDTO(
                    findedRoomData.getId(),
                    findedRoomData.getFurniture(),
                    findUserResponse.getNickName(),
                    findUserResponse.getProfileImg());

            return findRoomDataResponse;
        }

        return null;
    }
}
