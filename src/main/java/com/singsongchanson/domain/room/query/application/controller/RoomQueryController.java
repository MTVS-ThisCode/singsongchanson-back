package com.singsongchanson.domain.room.query.application.controller;

import com.singsongchanson.domain.room.command.application.dto.FindRoomDataResponseDTO;
import com.singsongchanson.domain.room.command.application.dto.RoomOwnerResponseDTO;
import com.singsongchanson.domain.room.query.application.service.RoomQueryService;
import com.singsongchanson.global.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
@Tag(name = "RoomQueryController", description = "싱송룸 API Document")
public class RoomQueryController {

    private final RoomQueryService roomQueryService;

    /** 모든 Room 목록 조회 */
    @Operation(summary = "모든 싱송룸 조회 요청", description = "모든 싱송룸을 조회합니다.", tags = { "RoomQueryController" })
    @GetMapping
    public ApiResponse findAllRooms() {

        List<RoomOwnerResponseDTO> roomResponse = roomQueryService.findAllRooms();

        return ApiResponse.success("성공적으로 조회되었습니다.", roomResponse);
    }

    /** roomId로 RoomData 상세 조회 */
    @Operation(summary = "싱송룸 데이터 조회 요청", description = "싱송룸 상세 데이터를 조회합니다.", tags = { "RoomQueryController" })
    @GetMapping("/{roomId}")
    public ApiResponse findRoomData(@PathVariable String roomId) {

        FindRoomDataResponseDTO findRoomDataResponse = roomQueryService.findRoomDataById(roomId);

        return ApiResponse.success("성공적으로 조회되었습니다.", findRoomDataResponse);
    }
}
