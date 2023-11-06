package com.singsongchanson.room.query.application.service;

import com.singsongchanson.domain.room.command.application.dto.CreateRoomRequestDTO;
import com.singsongchanson.domain.room.command.application.dto.RoomDataResponseDTO;
import com.singsongchanson.domain.room.command.application.dto.RoomResponseDTO;
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
public class RoomQueryServiceTests {

    @Autowired
    private RoomCommandService roomCommandService;
    @Autowired
    private RoomQueryService roomQueryService;

    @Test
    @DisplayName("싱송룸 조회 기능: Room 전체 조회")
    void findAllRooms() {

        // given
        List<Furniture> furnitureList = new ArrayList<>();
        furnitureList.add(0, new Furniture("guitar.glb", new Axis(1.0, 1.1, 1.2), new Axis(2.0, 2.1, 2.2), "/assets"));
        furnitureList.add(1, new Furniture("speaker.glb", new Axis(1.0, 1.1, 1.2), new Axis(2.0, 2.1, 2.2), "/assets"));

        CreateRoomRequestDTO createRoomRequestDTO = new CreateRoomRequestDTO(
                "153f02eac0c50c0e6bce7c1b", "아바타", furnitureList, "12");

        roomCommandService.saveRoom(createRoomRequestDTO, 12L);

        // when
        List<RoomResponseDTO> roomList = roomQueryService.findAllRooms();

        // then
        Assertions.assertEquals(1, roomList.size());
    }

    @Test
    @DisplayName("싱송룸 조회 기능: RoomId로 Room 상세 조회")
    void findRoomByRoomId() {

        // given
        List<Furniture> furnitureList = new ArrayList<>();
        furnitureList.add(0, new Furniture("guitar.glb", new Axis(1.0, 1.1, 1.2), new Axis(2.0, 2.1, 2.2), "/assets"));
        furnitureList.add(1, new Furniture("speaker.glb", new Axis(1.0, 1.1, 1.2), new Axis(2.0, 2.1, 2.2), "/assets"));

        CreateRoomRequestDTO createRoomRequestDTO = new CreateRoomRequestDTO(
                "853f02eac0c50c0e6bce7c1b", "아바타", furnitureList, "12");

        roomCommandService.saveRoom(createRoomRequestDTO, 12L);

        // when
        RoomResponseDTO room = roomQueryService.findRoomByRoomId("853f02eac0c50c0e6bce7c1b");

        // then
        Assertions.assertEquals(12L, room.getUserNo());
    }

    @Test
    @DisplayName("싱송룸 조회 기능: RoomId로 RoomData 상세 조회")
    void findRoomDataByRoomId() {

        // given
        List<Furniture> furnitureList = new ArrayList<>();
        furnitureList.add(0, new Furniture("guitar.glb", new Axis(1.0, 1.1, 1.2), new Axis(2.0, 2.1, 2.2), "/assets"));
        furnitureList.add(1, new Furniture("speaker.glb", new Axis(1.0, 1.1, 1.2), new Axis(2.0, 2.1, 2.2), "/assets"));

        CreateRoomRequestDTO createRoomRequestDTO = new CreateRoomRequestDTO(
                "953f02eac0c50c0e6bce7c1b", "아바타", furnitureList, "12");

        roomCommandService.saveRoom(createRoomRequestDTO, 12L);

        // when
        RoomDataResponseDTO roomData = roomQueryService.findRoomDataById("953f02eac0c50c0e6bce7c1b");

        // then
        Assertions.assertEquals("아바타", roomData.getAvatar());
        Assertions.assertEquals("guitar.glb", roomData.getFurniture().get(0).getName());
    }
}
