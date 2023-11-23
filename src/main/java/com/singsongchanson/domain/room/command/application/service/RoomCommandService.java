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

    public RoomDataResponseDTO createRoom(Long userNo) {

        Optional<Room> optionalRoom = roomRepository.findByRoomOwnerVO(new RoomOwnerVO(userNo));

        if(optionalRoom.isPresent()) {
            String roomId = optionalRoom.get().getRoomId();
            RoomData roomData = roomDataRepository.findById(roomId).get();
            return RoomDataResponseDTO.from(roomData);
        }

        RoomData roomData = RoomData.builder()
                .build();

        RoomData savedRoomData = roomDataRepository.save(roomData);

        Room room = Room.builder()
                .roomId(savedRoomData.getId())
                .roomOwnerVO(new RoomOwnerVO(userNo))
                .build();

        roomRepository.save(room);

        return RoomDataResponseDTO.from(savedRoomData);
    }

    public RoomDataResponseDTO saveRoom(CreateRoomRequestDTO createRoomRequest, Long requestUserNo) {

        Room room = Room.builder()
                .roomId(createRoomRequest.getRoomId())
                .roomOwnerVO(new RoomOwnerVO(requestUserNo))
                .build();

        roomRepository.save(room);

        RoomData roomData = RoomData.builder()
                .id(createRoomRequest.getRoomId())
                .furniture(createRoomRequest.getFurniture())
                .build();

        roomDataRepository.save(roomData);

        return RoomDataResponseDTO.from(roomData);
    }

    public RoomDataResponseDTO updateRoomData(String roomId, UpdateRoomDataRequestDTO updateRoomDataRequest,
                                              Long requestUserNo) {

        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        Optional<RoomData> optionalRoomData = roomDataRepository.findById(roomId);

        if (optionalRoom.isPresent() && optionalRoomData.isPresent()) {
            Room room = optionalRoom.get();
            RoomData roomData = optionalRoomData.get();

            if (room.getRoomOwnerVO().getUserNo() == requestUserNo) {

                roomData.update(updateRoomDataRequest);
                roomDataRepository.save(roomData);

                return RoomDataResponseDTO.from(roomData);
            }
        }

        return null;    /** 업데이트 실패 코드 보내도록 리팩토링 */
    }

    public boolean deleteRoom(String roomId, Long requestUserNo) {

        Optional<Room> optionalRoom = roomRepository.findById(roomId);

        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            if (room.getRoomOwnerVO().getUserNo() == requestUserNo) {
                roomDataRepository.deleteById(roomId);
                roomRepository.deleteById(roomId);

                return true;
            }
        }
        return false;
    }
}
