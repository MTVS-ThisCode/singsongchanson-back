package com.singsongchanson.room.command.application.service;

import com.singsongchanson.domain.room.command.application.dto.*;
import com.singsongchanson.domain.room.command.application.service.RoomCommandService;
import com.singsongchanson.domain.room.command.domain.aggregate.entity.Room;
import com.singsongchanson.domain.room.command.domain.aggregate.entity.RoomData;
import com.singsongchanson.domain.room.command.domain.aggregate.vo.Axis;
import com.singsongchanson.domain.room.command.domain.aggregate.vo.Furniture;
import com.singsongchanson.domain.room.command.domain.repository.RoomDataRepository;
import com.singsongchanson.domain.room.command.domain.repository.RoomRepository;
import com.singsongchanson.domain.room.query.application.service.RoomQueryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class RoomCommandServiceTests {

    @Autowired
    private RoomCommandService roomCommandService;
    @Autowired
    private RoomQueryService roomQueryService;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomDataRepository roomDataRepository;

    @Test
    @DisplayName("싱송룸 저장 테스트: Room, RoomData 저장")
    void saveRoom() {

        // given
        List<Furniture> furnitureList = new ArrayList<>();
        furnitureList.add(0, new Furniture("guitar.glb", new Axis(1.0,1.1,1.2), new Axis(2.0, 2.1, 2.2), "/assets", 0.1));
        furnitureList.add(1, new Furniture("speaker.glb", new Axis(1.0,1.1,1.2), new Axis(2.0, 2.1, 2.2), "/assets",0.1));

        CreateRoomRequestDTO createRoomRequestDTO = new CreateRoomRequestDTO(
                "654c7e727a3075241d9057f9", furnitureList);

        roomCommandService.saveRoom(createRoomRequestDTO, 12L);

        // when
        Optional<Room> room = roomRepository.findById(createRoomRequestDTO.getRoomId());
        Optional<RoomData> roomData = roomDataRepository.findById(createRoomRequestDTO.getRoomId());

        // then
        Assertions.assertNotNull(room);
        Assertions.assertNotNull(roomData);
    }

    @Test
    @DisplayName("싱송룸 수정 테스트: RoomData 업데이트")
    void updateRoomData() {

        // given
        List<Furniture> furnitureList = new ArrayList<>();
        furnitureList.add(0, new Furniture("guitar.glb", new Axis(1.0,1.1,1.2), new Axis(2.0, 2.1, 2.2), "/assets", 0.1));

        CreateRoomRequestDTO createRoomRequestDTO = new CreateRoomRequestDTO(
                "553f02eac0c50c0e6bce7c1b", furnitureList);

        roomCommandService.saveRoom(createRoomRequestDTO, 12L);

        // when
        List<Furniture> furnitureList2 = new ArrayList<>();
        furnitureList2.add(0, new Furniture("speaker.glb", new Axis(1.0,1.1,1.2), new Axis(2.0, 2.1, 2.2), "/assets", 0.1));

        UpdateRoomDataRequestDTO updateRoomDataRequestDTO = new UpdateRoomDataRequestDTO(furnitureList2);

        roomCommandService.updateRoomData("553f02eac0c50c0e6bce7c1b", updateRoomDataRequestDTO, 12L);

        Optional<RoomData> roomData = roomDataRepository.findById("553f02eac0c50c0e6bce7c1b");

        // then
        Assertions.assertEquals("speaker.glb", roomData.get().getFurniture().get(0).getName());
    }

    @Test
    @DisplayName("싱송룸 삭제 테스트: Room, RoomData 삭제")
    void deleteRoom() {

        // given
        List<Furniture> furnitureList = new ArrayList<>();
        furnitureList.add(0, new Furniture("guitar.glb", new Axis(1.0,1.1,1.2), new Axis(2.0, 2.1, 2.2), "/assets", 0.1));

        CreateRoomRequestDTO createRoomRequestDTO = new CreateRoomRequestDTO(
                "253f02eac0c50c0e6bce7c1b", furnitureList);

        roomCommandService.saveRoom(createRoomRequestDTO, 12L);

        // when
        roomCommandService.deleteRoom("253f02eac0c50c0e6bce7c1b", 12L);

        Optional<Room> room = roomRepository.findById(createRoomRequestDTO.getRoomId());
        Optional<RoomData> roomData = roomDataRepository.findById(createRoomRequestDTO.getRoomId());

        // then

        Assertions.assertTrue(room.isEmpty());
        Assertions.assertTrue(roomData.isEmpty());
    }
}
