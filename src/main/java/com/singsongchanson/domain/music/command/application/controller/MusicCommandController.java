package com.singsongchanson.domain.music.command.application.controller;

import com.singsongchanson.domain.music.command.application.dto.AiMusicImageRequestDTO;
import com.singsongchanson.domain.music.command.application.dto.AiMusicRequestDTO;
import com.singsongchanson.domain.music.command.application.dto.MusicResponseDTO;
import com.singsongchanson.domain.music.command.application.service.MusicCommandService;
import com.singsongchanson.global.common.response.ApiResponse;
import com.singsongchanson.global.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/musics")
public class MusicCommandController {

    private final MusicCommandService musicCommandService;
    @PostMapping()
    public ApiResponse createMusic(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                   AiMusicRequestDTO aiMusicRequest) {

        MusicResponseDTO musicResponse = musicCommandService.saveAiMusic(userPrincipal, aiMusicRequest);

        return ApiResponse.success("성공적으로 저장되었습니다.", musicResponse);
    }

    @PostMapping("/image")
    public ApiResponse createMusicByImage(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                          @RequestBody AiMusicImageRequestDTO aiMusicImageRequest) {

        MusicResponseDTO musicResponse = musicCommandService.saveAiMusicByImage(userPrincipal, aiMusicImageRequest);

        return ApiResponse.success("성공적으로 저장되었습니다.", musicResponse);
    }

    @PutMapping("/count")
    public ApiResponse updateMusic(@RequestParam Long musicNo) {

        MusicResponseDTO musicResponseDTO = musicCommandService.updateStreamingCnt(musicNo);

        return ApiResponse.success("성공적으로 수정되었습니다.", musicResponseDTO);
    }
}
