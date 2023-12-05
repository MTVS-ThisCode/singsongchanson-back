package com.singsongchanson.domain.music.query.application.controller;

import com.singsongchanson.domain.music.command.application.dto.MusicResponseDTO;
import com.singsongchanson.domain.music.command.application.dto.MusicRoomResponseDTO;
import com.singsongchanson.domain.music.command.domain.aggregate.vo.GenerateUserVO;
import com.singsongchanson.domain.music.query.application.service.MusicQueryService;
import com.singsongchanson.global.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/musics")
@RequiredArgsConstructor
@Tag(name = "MusicQueryController", description = "음악 API Document")
public class MusicQueryController {

    private final MusicQueryService musicQueryService;

    /** 음악 전체 조회 */
    @Operation(summary = "모든 음악 조회", description = "모든 음악을 조회합니다.", tags = { "MusicQueryController" })
    @GetMapping
    public ApiResponse findMusics() {

        List<MusicResponseDTO> musicResponse = musicQueryService.findAllMusics();

        return ApiResponse.success("성공적으로 조회되었습니다.", musicResponse);
    }

    /** 음악 상세 조회 */
    @GetMapping("/{musicNo}")
    public ApiResponse findMusic(@PathVariable Long musicNo) {

        MusicResponseDTO musicResponse = musicQueryService.findMusicByMusicNo(musicNo);

        return ApiResponse.success("성공적으로 조회되었습니다.", musicResponse);
    }

    /** userNo로 내 음악 조회 */
    @Operation(summary = "내 음악 조회", description = "내가 생성한 음악을 조회합니다.", tags = { "MusicQueryController" })
    @GetMapping("/myMusic")
    public ApiResponse findMusicByUserNo(@RequestParam Long userNo) {

        List<MusicResponseDTO> musicResponse = musicQueryService.findMusicByGenerateUserVO(new GenerateUserVO(userNo));

        return ApiResponse.success("성공적으로 조회되었습니다.", musicResponse);
    }

    @Operation(summary = "음악 차트 조회", description = "재생 횟수 기반으로 랭크된 음악 차트를 조회합니다.", tags = { "MusicQueryController" })
    @GetMapping("/ranking")
    public ApiResponse findMusicByStreamingCntDesc() {

        List<MusicRoomResponseDTO> musicResponse = musicQueryService.findMusicByStreamingCntDesc();

        return ApiResponse.success("성공적으로 조회되었습니다.", musicResponse);
    }

}
