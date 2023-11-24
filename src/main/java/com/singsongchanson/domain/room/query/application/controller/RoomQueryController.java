package com.singsongchanson.domain.room.query.application.controller;

import com.singsongchanson.domain.room.command.application.dto.FindRoomDataResponseDTO;
import com.singsongchanson.domain.room.command.application.dto.RoomOwnerResponseDTO;
import com.singsongchanson.domain.room.query.application.service.RoomQueryService;
import com.singsongchanson.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomQueryController {

    private final RoomQueryService roomQueryService;

    /** 모든 Room 목록 조회 */
    @GetMapping
    public ApiResponse findAllRooms() {

        List<RoomOwnerResponseDTO> roomResponse = roomQueryService.findAllRooms();

        return ApiResponse.success("성공적으로 조회되었습니다.", roomResponse);
    }

    /** roomId로 RoomData 상세 조회 */
    @GetMapping("/{roomId}")
    public ApiResponse findRoomData(@PathVariable String roomId) {

        FindRoomDataResponseDTO findRoomDataResponse = roomQueryService.findRoomDataById(roomId);

        return ApiResponse.success("성공적으로 조회되었습니다.", findRoomDataResponse);
    }
}
