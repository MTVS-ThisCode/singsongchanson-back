package com.singsongchanson.domain.music.command.application.controller;

import com.singsongchanson.domain.music.command.application.dto.AiMusicRequestDTO;
import com.singsongchanson.domain.music.command.application.dto.MusicResponseDTO;
import com.singsongchanson.domain.music.command.application.service.MusicCommandService;
import com.singsongchanson.global.common.response.ApiResponse;
import com.singsongchanson.global.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/musics")
public class MusicCommandController {

    private final MusicCommandService musicCommandService;
    @PostMapping()
    public ApiResponse createMusic(@AuthenticationPrincipal UserPrincipal userPrincipal, AiMusicRequestDTO aiMusicRequest) {

        System.out.println("aiMusicRequest = " + aiMusicRequest);
        MusicResponseDTO musicResponse = musicCommandService.saveAiMusic(userPrincipal, aiMusicRequest);

        return ApiResponse.success("성공적으로 저장되었습니다.", musicResponse);
    }

//    @PutMapping("/{musicNo}")
//    public ApiResponse updateMusic(UpdateMusicRequestDTO updateMusicRequest) {
//        MusicResponseDTO musicResponseDTO = musicCommandService.updateMusic(updateMusicRequest);
//
//        return ApiResponse.success("성공적으로 수정되었습니다.", musicResponseDTO);
//    }
}
