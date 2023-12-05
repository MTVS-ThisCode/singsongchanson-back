package com.singsongchanson.domain.music.command.application.controller;

import com.singsongchanson.domain.music.command.application.dto.AiMusicImageRequestDTO;
import com.singsongchanson.domain.music.command.application.dto.AiMusicRequestDTO;
import com.singsongchanson.domain.music.command.application.dto.MusicResponseDTO;
import com.singsongchanson.domain.music.command.application.service.MusicCommandService;
import com.singsongchanson.global.common.response.ApiResponse;
import com.singsongchanson.global.security.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/musics")
@Tag(name = "MusicCommandController", description = "음악 API Document")
public class MusicCommandController {

    private final MusicCommandService musicCommandService;

    @Operation(summary = "Ai 음악 생성 요청(키워드)", description = "키워드, 장르 등으로 음악 생성 요청합니다.", tags = { "MusicCommandController" })
    @PostMapping()
    public ApiResponse createMusic(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                   AiMusicRequestDTO aiMusicRequest) {

        MusicResponseDTO musicResponse = musicCommandService.saveAiMusic(userPrincipal, aiMusicRequest);

        return ApiResponse.success("성공적으로 저장되었습니다.", musicResponse);
    }

    @Operation(summary = "Ai 음악 생성 요청(이미지)", description = "이미지로 음악 생성 요청합니다.", tags = { "MusicCommandController" })
    @PostMapping("/image")
    public ApiResponse createMusicByImage(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                          AiMusicImageRequestDTO aiMusicImageRequest) {

        MusicResponseDTO musicResponse = musicCommandService.saveAiMusicByImage(userPrincipal, aiMusicImageRequest);

        return ApiResponse.success("성공적으로 저장되었습니다.", musicResponse);
    }

    @Operation(summary = "음악 재생횟수 업데이트 요청", description = "음악 재생횟수가 1회씩 증가합니다.", tags = { "MusicCommandController" })
    @PutMapping("/count")
    public ApiResponse updateMusic(@RequestParam Long musicNo) {

        MusicResponseDTO musicResponseDTO = musicCommandService.updateStreamingCnt(musicNo);

        return ApiResponse.success("성공적으로 수정되었습니다.", musicResponseDTO);
    }
}
