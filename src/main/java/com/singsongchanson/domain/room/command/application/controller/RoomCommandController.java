package com.singsongchanson.domain.room.command.application.controller;

import com.singsongchanson.domain.room.command.application.dto.CreateRoomRequestDTO;
import com.singsongchanson.domain.room.command.application.dto.RoomDataResponseDTO;
import com.singsongchanson.domain.room.command.application.dto.UpdateRoomDataRequestDTO;
import com.singsongchanson.domain.room.command.application.service.RoomCommandService;
import com.singsongchanson.global.common.response.ApiResponse;
import com.singsongchanson.global.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomCommandController {

    private final RoomCommandService roomCommandService;

    @PostMapping
    public ApiResponse createRoom(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                  CreateRoomRequestDTO createRoomRequest) {

        RoomDataResponseDTO roomDataResponse = roomCommandService.saveRoom(createRoomRequest, userPrincipal.getId());

        return ApiResponse.success("성공적으로 저장되었습니다.", roomDataResponse);
    }

    @PutMapping("/{roomId}")
    public ApiResponse updateRoom(@PathVariable String roomId,
                                  @AuthenticationPrincipal UserPrincipal userPrincipal,
                                  UpdateRoomDataRequestDTO updateRoomDataRequestDTO) {

        RoomDataResponseDTO roomDataResponse = roomCommandService.updateRoomData(roomId, updateRoomDataRequestDTO, userPrincipal.getId());

        return ApiResponse.success("성공적으로 수정되었습니다.", roomDataResponse);
    }

    @DeleteMapping("/{roomId}")
    public ApiResponse deleteRoom(@PathVariable String roomId,
                                  @AuthenticationPrincipal UserPrincipal userPrincipal) {

        roomCommandService.deleteRoom(roomId, userPrincipal.getId());

        return ApiResponse.success("성공적으로 삭제되었습니다.", null);
    }
}
