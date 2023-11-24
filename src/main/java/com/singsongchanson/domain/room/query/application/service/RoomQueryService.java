package com.singsongchanson.domain.room.query.application.service;

import com.singsongchanson.domain.room.command.application.dto.FindRoomDataResponseDTO;
import com.singsongchanson.domain.room.command.application.dto.RoomOwnerResponseDTO;
import com.singsongchanson.domain.room.command.application.dto.RoomResponseDTO;
import com.singsongchanson.domain.room.command.domain.aggregate.entity.Room;
import com.singsongchanson.domain.room.command.domain.aggregate.entity.RoomData;
import com.singsongchanson.domain.room.command.domain.aggregate.vo.RoomOwnerVO;
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

    public List<RoomOwnerResponseDTO> findAllRooms() {

        List<Room> roomList = roomRepository.findAll();

        List<RoomOwnerResponseDTO> roomResponseList = new ArrayList<>();

        for (Room room : roomList) {
            Long userNo = room.getRoomOwnerVO().getUserNo();
            FindUserResponseDTO findUserResponse = roomQueryDomainService.getUserInfo(userNo);

            RoomOwnerResponseDTO roomOwnerResponseDTO = new RoomOwnerResponseDTO(
                    room.getRoomId(),
                    userNo,
                    findUserResponse.getNickName(),
                    findUserResponse.getProfileImg()
            );
            roomResponseList.add(roomOwnerResponseDTO);
        }
        return roomResponseList;
    }

    public FindRoomDataResponseDTO findRoomDataById(String roomId) {

        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        Optional<RoomData> optionalRoomData = roomDataRepository.findById(roomId);

        if (optionalRoom.isPresent() && optionalRoomData.isPresent()) {
            Long userNo = optionalRoom.get().getRoomOwnerVO().getUserNo();
            RoomData roomData = optionalRoomData.get();
            FindUserResponseDTO findUserResponse = roomQueryDomainService.getUserInfo(userNo);

            FindRoomDataResponseDTO findRoomDataResponse = new FindRoomDataResponseDTO(
                    roomData.getId(),
                    roomData.getFurniture(),
                    findUserResponse.getUserNo(),
                    findUserResponse.getNickName(),
                    findUserResponse.getProfileImg());

            return findRoomDataResponse;
        }

        return null;    // 낫파운드
    }

    public RoomResponseDTO findRoomByRoomOwnerVO(Long userNo) {

         RoomResponseDTO roomResponse = roomRepository.findByRoomOwnerVO(new RoomOwnerVO(userNo))
                 .map(RoomResponseDTO::from)
                 .orElseThrow(() -> new IllegalArgumentException("해당하는 음악이 없습니다."));

         return roomResponse;


    }
}
