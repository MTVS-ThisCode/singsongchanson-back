package com.singsongchanson.domain.room.command.application.controller;

import com.singsongchanson.domain.room.command.application.dto.CreateRoomRequestDTO;
import com.singsongchanson.domain.room.command.application.dto.RoomDataResponseDTO;
import com.singsongchanson.domain.room.command.application.dto.UpdateRoomDataRequestDTO;
import com.singsongchanson.domain.room.command.application.service.RoomCommandService;
import com.singsongchanson.global.common.response.ApiResponse;
import com.singsongchanson.global.security.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
@Tag(name = "RoomCommandController", description = "싱송룸 API Document")
public class RoomCommandController {

    private final RoomCommandService roomCommandService;

    @Operation(summary = "싱송룸 생성 요청", description = "싱송룸을 생성합니다.", tags = { "RoomCommandController" })
    @PostMapping
    public ApiResponse createRoom(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                  @RequestBody CreateRoomRequestDTO createRoomRequest) {

        RoomDataResponseDTO roomDataResponse = roomCommandService.saveRoom(createRoomRequest, userPrincipal.getId());

        return ApiResponse.success("성공적으로 저장되었습니다.", roomDataResponse);
    }

    @Operation(summary = "싱송룸 수정 요청", description = "싱송룸을 수정합니다.", tags = { "RoomCommandController" })
    @PutMapping("/{roomId}")
    public ApiResponse updateRoom(@PathVariable String roomId,
                                  @AuthenticationPrincipal UserPrincipal userPrincipal,
                                  @RequestBody UpdateRoomDataRequestDTO updateRoomDataRequestDTO) {

        RoomDataResponseDTO roomDataResponse = roomCommandService.updateRoomData(roomId, updateRoomDataRequestDTO, userPrincipal.getId());

        return ApiResponse.success("성공적으로 수정되었습니다.", roomDataResponse);
    }

    @Operation(summary = "싱송룸 삭제 요청", description = "싱송룸을 삭제합니다.", tags = { "RoomCommandController" })
    @DeleteMapping("/{roomId}")
    public ApiResponse deleteRoom(@PathVariable String roomId,
                                  @AuthenticationPrincipal UserPrincipal userPrincipal) {

        boolean result = roomCommandService.deleteRoom(roomId, userPrincipal.getId());

        return ApiResponse.success("성공적으로 삭제되었습니다.", result);
    }
}
