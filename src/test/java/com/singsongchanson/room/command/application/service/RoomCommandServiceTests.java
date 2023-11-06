package com.singsongchanson.room.command.application.service;

import com.singsongchanson.domain.room.command.application.dto.CreateRoomRequestDTO;
import com.singsongchanson.domain.room.command.application.dto.RoomDataResponseDTO;
import com.singsongchanson.domain.room.command.application.dto.RoomResponseDTO;
import com.singsongchanson.domain.room.command.application.dto.UpdateRoomDataRequestDTO;
import com.singsongchanson.domain.room.command.application.service.RoomCommandService;
import com.singsongchanson.domain.room.command.domain.aggregate.vo.Axis;
import com.singsongchanson.domain.room.command.domain.aggregate.vo.Furniture;
import com.singsongchanson.domain.room.query.application.service.RoomQueryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class RoomCommandServiceTests {

    @Autowired
    private RoomCommandService roomCommandService;

    @Autowired
    private RoomQueryService roomQueryService;

    @Test
    @DisplayName("싱송룸 저장 테스트: Room, RoomData 저장")
    void saveRoom() {

        // given
        List<Furniture> furnitureList = new ArrayList<>();
        furnitureList.add(0, new Furniture("guitar.glb", new Axis(1.0,1.1,1.2), new Axis(2.0, 2.1, 2.2), "/assets"));
        furnitureList.add(1, new Furniture("speaker.glb", new Axis(1.0,1.1,1.2), new Axis(2.0, 2.1, 2.2), "/assets"));

        CreateRoomRequestDTO createRoomRequestDTO = new CreateRoomRequestDTO(
                "153f02eac0c50c0e6bce7c1b", "아바타", furnitureList, "12");

        roomCommandService.saveRoom(createRoomRequestDTO, 12L);

        // when
        RoomResponseDTO room = roomQueryService.findRoomByRoomId(createRoomRequestDTO.getRoomId());
        RoomDataResponseDTO roomData = roomQueryService.findRoomDataById(createRoomRequestDTO.getRoomId());

        // then
        Assertions.assertNotNull(room);
        Assertions.assertNotNull(roomData);
    }

    @Test
    @DisplayName("싱송룸 수정 테스트: RoomData 업데이트")
    void updateRoomData() {

        // given
        List<Furniture> furnitureList = new ArrayList<>();
        furnitureList.add(0, new Furniture("guitar.glb", new Axis(1.0,1.1,1.2), new Axis(2.0, 2.1, 2.2), "/assets"));

        CreateRoomRequestDTO createRoomRequestDTO = new CreateRoomRequestDTO(
                "553f02eac0c50c0e6bce7c1b", "아바타", furnitureList, "12");

        roomCommandService.saveRoom(createRoomRequestDTO, 12L);

        // when
        List<Furniture> furnitureList2 = new ArrayList<>();
        furnitureList2.add(0, new Furniture("speaker.glb", new Axis(1.0,1.1,1.2), new Axis(2.0, 2.1, 2.2), "/assets"));

        UpdateRoomDataRequestDTO updateRoomDataRequestDTO = new UpdateRoomDataRequestDTO("아바타2", furnitureList2);

        roomCommandService.updateRoomData("553f02eac0c50c0e6bce7c1b", updateRoomDataRequestDTO, 12L);

        RoomDataResponseDTO roomData = roomQueryService.findRoomDataById("553f02eac0c50c0e6bce7c1b");

        // then
        Assertions.assertEquals("아바타2", roomData.getAvatar());
        Assertions.assertEquals("speaker.glb", roomData.getFurniture().get(0).getName());
    }

    @Test
    @DisplayName("싱송룸 삭제 테스트: Room, RoomData 삭제")
    void deleteRoom() {

        // given
        List<Furniture> furnitureList = new ArrayList<>();
        furnitureList.add(0, new Furniture("guitar.glb", new Axis(1.0,1.1,1.2), new Axis(2.0, 2.1, 2.2), "/assets"));

        CreateRoomRequestDTO createRoomRequestDTO = new CreateRoomRequestDTO(
                "253f02eac0c50c0e6bce7c1b", "아바타", furnitureList, "12");

        roomCommandService.saveRoom(createRoomRequestDTO, 12L);

        // when
        roomCommandService.deleteRoom("253f02eac0c50c0e6bce7c1b", 12L);

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> roomQueryService.findRoomByRoomId(createRoomRequestDTO.getRoomId()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> roomQueryService.findRoomDataById(createRoomRequestDTO.getRoomId()));
    }
}
