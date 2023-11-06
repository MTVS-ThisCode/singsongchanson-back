package com.singsongchanson.domain.room.command.application.service;

import com.singsongchanson.domain.room.command.application.dto.CreateRoomRequestDTO;
import com.singsongchanson.domain.room.command.application.dto.RoomDataResponseDTO;
import com.singsongchanson.domain.room.command.application.dto.UpdateRoomDataRequestDTO;
import com.singsongchanson.domain.room.command.domain.aggregate.entity.Room;
import com.singsongchanson.domain.room.command.domain.aggregate.entity.RoomData;
import com.singsongchanson.domain.room.command.domain.aggregate.vo.RoomOwnerVO;
import com.singsongchanson.domain.room.command.domain.repository.RoomRepository;
import com.singsongchanson.domain.room.command.domain.repository.RoomDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomCommandService {

    private final RoomRepository roomRepository;
    private final RoomDataRepository roomDataRepository;

    public RoomDataResponseDTO saveRoom(CreateRoomRequestDTO createRoomRequest, Long requestUserNo) {

        Room room = Room.builder()
                .roomId(createRoomRequest.getRoomId())
                .roomOwnerVO(new RoomOwnerVO(requestUserNo))
                .build();

        roomRepository.save(room);

        RoomData roomData = RoomData.builder()
                .id(createRoomRequest.getRoomId())
                .avatar(createRoomRequest.getAvatar())
                .furniture(createRoomRequest.getFurniture())
                .userNo(createRoomRequest.getUserNo())
                .build();

        roomDataRepository.save(roomData);

        return RoomDataResponseDTO.from(roomData);
    }

    public RoomDataResponseDTO updateRoomData(String roomId, UpdateRoomDataRequestDTO updateRoomDataRequest,
                                              Long requestUserNo) {

        Optional<Room> room = roomRepository.findById(roomId);
        Optional<RoomData> roomData = roomDataRepository.findById(roomId);

        if (room.isPresent() && roomData.isPresent()) {
            Room findedRoom = room.get();
            RoomData findedRoomData = roomData.get();

            if (findedRoom.getRoomOwnerVO().getUserNo() == requestUserNo) {

                findedRoomData.update(updateRoomDataRequest);     /** 각각 업데이트 할 수 있도록 수정 */
                roomDataRepository.save(findedRoomData);

                return RoomDataResponseDTO.from(findedRoomData);
            }
        }

        return null;    /** 업데이트 실패 코드 보내도록 리팩토링 */
    }

    public void deleteRoom(String roomId, Long requestUserNo) {

        Optional<Room> room = roomRepository.findById(roomId);

        if (room.isPresent()) {
            Room findedRoom = room.get();
            if (findedRoom.getRoomOwnerVO().getUserNo() == requestUserNo) {
                roomDataRepository.deleteById(roomId);
                roomRepository.deleteById(roomId);
            }
        }
    }
}
